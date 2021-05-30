package fr.semanteo.myapplication.fragments

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import fr.semanteo.myapplication.MainActivity
import fr.semanteo.myapplication.R
import java.text.MessageFormat

class AboutFragment(private val context: MainActivity): Fragment(){

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_about, container, false)
        val txt= view.findViewById<TextView>(R.id.about_git);
        txt.movementMethod = LinkMovementMethod.getInstance();
        txt.setOnClickListener {onClick()};

        val name= view.findViewById<TextView>(R.id.about_name);
        val vers= view.findViewById<TextView>(R.id.about_version);
        val dev= view.findViewById<TextView>(R.id.about_dev);
        val subt= view.findViewById<TextView>(R.id.about_subtitle);
        val mod= view.findViewById<TextView>(R.id.about_date);

        val nam = context.resources.getString(R.string.app_name);
        val ver = context.resources.getString(R.string.app_version);
        val de = context.resources.getString(R.string.app_dev);
        val date = context.resources.getString(R.string.app_last);

        name.text  = MessageFormat.format("Nom : {0}", nam);
        vers.text = MessageFormat.format("Version : {0}", ver);
        dev.text = MessageFormat.format("Développeur : {0}", de);
        subt.text = MessageFormat.format("{0} par {1}", nam, de);
        mod.text = MessageFormat.format("Dernière modification : {0}", date);

        return view
    }
    private fun onClick() {
        val browserIntent = Intent(Intent.ACTION_VIEW);
        browserIntent.data = Uri.parse("https://github.com/Semanteo/Wine");
        startActivity(browserIntent);
    }

}