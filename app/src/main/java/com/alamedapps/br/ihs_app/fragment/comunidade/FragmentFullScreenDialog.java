package com.alamedapps.br.ihs_app.fragment.comunidade;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

public class FragmentFullScreenDialog extends DialogFragment implements View.OnClickListener {

    private Callback callback;

    private TextView titleOracaoTv;
    private TextView letraOracaoTv;

    private String nomeOracaoParam;
    private String letraOracaoParam;

    public static FragmentFullScreenDialog newInstance() {
        return new FragmentFullScreenDialog();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
        Bundle b = getArguments();
        nomeOracaoParam = b.getString("nome");
        letraOracaoParam = b.getString("letraOracao");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fullscreen_dialog, container, false);

        ImageButton close = view.findViewById(R.id.fullscreen_dialog_close);

        titleOracaoTv = view.findViewById(R.id.title_oracao_modal_full_screen);
        letraOracaoTv = view.findViewById(R.id.letra_oracao);

        titleOracaoTv.setText(nomeOracaoParam);
        letraOracaoTv.setText(letraOracaoParam);

        close.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.fullscreen_dialog_close:
                dismiss();
                break;

        }

    }

    public interface Callback {

        void onActionClick(String name);

    }
}
