package com.alamedapps.br.ihs_app.fragment.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

/**
 * Fragment da seção About
 */
public class FragmentFullScreenDialogAbout extends DialogFragment implements View.OnClickListener {

    private LinearLayout layoutEmailContactButton;

    public static FragmentFullScreenDialogAbout newInstance() {
        return new FragmentFullScreenDialogAbout();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fullscreen_dialog_about, container, false);

        ImageButton close = view.findViewById(R.id.fullscreen_dialog_close_about);

        close.setOnClickListener(this);

        layoutEmailContactButton = view.findViewById(R.id.linearLayout_email_send);
        layoutEmailContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(getString(R.string.email_app));
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.fullscreen_dialog_close_about:
                dismiss();
                break;
        }
    }

    private void sendEmail(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.assunto_email));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Send Email"));
    }
}
