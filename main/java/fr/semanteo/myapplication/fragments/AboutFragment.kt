package fr.semanteo.myapplication.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import fr.semanteo.myapplication.MainActivity
import fr.semanteo.myapplication.R

class AboutFragment(private val context: MainActivity): Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_about, container, false)
        val txt= view.findViewById<TextView>(R.id.about_git); //txt is object of TextView
        txt.movementMethod = LinkMovementMethod.getInstance();
        txt.setOnClickListener {onClick()};

        return view
    }
    private fun onClick() {
        val browserIntent = Intent(Intent.ACTION_VIEW);
        browserIntent.data = Uri.parse("https://github.com/Semanteo/Wine");
        startActivity(browserIntent);
    }

}