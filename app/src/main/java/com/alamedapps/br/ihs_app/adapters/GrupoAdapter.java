package com.alamedapps.br.ihs_app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.models.igrejaemacao.Grupo;
import com.alamedapps.br.ihs_app.utils.FormatterUtils;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.alamedapps.br.ihs_app.adapters.viewholders.GrupoViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GrupoAdapter extends RecyclerView.Adapter implements Filterable {

    private static final int GROUP_TYPE = 1;
    private static final int HEADER_TYPE = 2;

    //Constantes de cada item do popup menu do card de cada movimento.
    private static final int DESCRICAO = 0;
    private static final int DOCUMENTOS = 1;
    private static final int COORDENADORES = 2;

    private List<Grupo> grupoList;
    private List<Grupo> grupoListFilted;

    private Context context;

    public GrupoAdapter(List<Grupo> grupoList, Context context) {
        if (grupoList != null) {
            this.grupoList = grupoList;
        } else {
            this.grupoList = new ArrayList<>();
        }
        this.context = context;
    }

    public void add(Grupo grupo) {
        grupoList.add(grupo);
        notifyItemInserted(grupoList.size() + 1);
    }

    public void clear() {
        grupoList = new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;

        switch (viewType) {

            case GROUP_TYPE:
                v = LayoutInflater.from(context).inflate(R.layout.layout_grupo, parent, false);
                //GrupoViewHolder grupoViewHolder = new GrupoViewHolder(v);
                return new GrupoViewHolder(v);
            case HEADER_TYPE:
                v = LayoutInflater.from(context).inflate(R.layout.layout_grupo_header, parent, false);
                //GrupoViewHolder grupoViewHolder = new GrupoViewHolder(v);
                return new GrupoViewHolder(v);
            default:
                v = LayoutInflater.from(context).inflate(R.layout.layout_grupo, parent, false);
                //GrupoViewHolder grupoViewHolder = new GrupoViewHolder(v);
                return new GrupoViewHolder(v);

        }


        //return grupoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final GrupoViewHolder grupoViewHolder = (GrupoViewHolder) holder;
        final Grupo grupo = grupoList.get(position);

//        grupoViewHolder.nome.setText(grupo.getNome());
//        grupoViewHolder.categoria.setText(grupo.getCategoriaGrupo().toString());

        int itemViewType = getItemViewType(position);

        if (itemViewType == GROUP_TYPE) {
            grupoViewHolder.nome.setText(grupo.getNome());
            grupoViewHolder.reuniao.setText(grupo.getReuniao());
        } else {
            String categFormatted = FormatterUtils.firstLetterUppercase(grupo.getCategoriaGrupo().toString());
            grupoViewHolder.categoria.setText(categFormatted);

            grupoViewHolder.nome.setText(grupo.getNome());
            grupoViewHolder.reuniao.setText(grupo.getReuniao());
        }

        popMenuCardGenerate(grupoViewHolder, grupo);

        if (grupo.getReuniao().trim().length() == 0) {
            grupoViewHolder.reuniao_label.setVisibility(View.GONE);
            grupoViewHolder.reuniao.setVisibility(View.GONE);
        } else {
            grupoViewHolder.reuniao_label.setVisibility(View.VISIBLE);
            grupoViewHolder.reuniao.setVisibility(View.VISIBLE);
        }
    }

    public void popMenuCardGenerate(final GrupoViewHolder grupoViewHolder, final Grupo grupo) {

        grupoViewHolder.menuCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, grupoViewHolder.menuCard);
                popupMenu.inflate(R.menu.card_menu);

                if (grupo.getDescricao().length() == 0)
                    popupMenu.getMenu().getItem(DESCRICAO).setVisible(false);

                if (grupo.getDocumentos() == null)
                    popupMenu.getMenu().getItem(DOCUMENTOS).setVisible(false);
                else {
                    String title = popupMenu.getMenu().getItem(DOCUMENTOS).getTitle().toString();
                    popupMenu.getMenu().getItem(DOCUMENTOS).setTitle(title + " (" + grupo.getDocumentos().size() + ")");
                }

                if (grupo.getCoordenadores() == null) {
                    popupMenu.getMenu().getItem(COORDENADORES).setVisible(false);
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.descricao_item:
                                IHSUtil.generateModalInfo(grupo.getNome(), grupo.getDescricao(), context);
                                break;
                            case R.id.documentos_item:
                                IHSUtil.generateModalInfoListItems(
                                        context.getString(R.string.docs_modal_label) + " " + grupo.getNome(),
                                        grupo.getDocumentos(),
                                        context
                                );
                                break;
                            case R.id.coodernadores_item:
                                List<String> keyValList = new ArrayList<>();

                                for (Map.Entry<String, String> entry : grupo.getCoordenadores().entrySet()) {
                                    String key = entry.getKey();
                                    String value = entry.getValue();
                                    keyValList.add(key + "\n" + context.getString(R.string.contato_modal_label) + " " + value);
                                }

                                IHSUtil.generateModalInfoListItems(
                                        context.getString(R.string.coord_modal_label) + " " + grupo.getNome(),
                                        keyValList,
                                        context
                                );
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        if (grupo.getDocumentos() == null && grupo.getDescricao().trim().length() == 0 && grupo.getCoordenadores() == null) {
            grupoViewHolder.menuCard.setVisibility(View.INVISIBLE);
        } else {
            grupoViewHolder.menuCard.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return grupoList != null ? grupoList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {

        int type = 0;

        if (position < grupoList.size() - 1) {
            if (position == 0 || grupoList.get(position).getCategoriaGrupo() != grupoList.get(position - 1).getCategoriaGrupo()) {
                type = HEADER_TYPE;
            } else if (grupoList.get(position).getCategoriaGrupo() == grupoList.get(position - 1).getCategoriaGrupo()) {
                type = GROUP_TYPE;
            } else {
                type = HEADER_TYPE;

            }
        } else {
            if (grupoList.size() > 1 && (grupoList.get(position).getCategoriaGrupo() == grupoList.get(position - 1).getCategoriaGrupo()))
                type = GROUP_TYPE;
            else
                type = HEADER_TYPE;
        }


        return type;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<Grupo> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    grupoListFilted = grupoList;
                    filteredList.addAll(grupoListFilted);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    if (grupoListFilted == null)
                        grupoListFilted = grupoList;

                    for (Grupo item : grupoListFilted) {
                        if (item.getNome().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                grupoList = (ArrayList<Grupo>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public List<Grupo> getGrupoListFilted() {
        return grupoListFilted;
    }
}
