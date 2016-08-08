package com.addantibes.addantibes;

import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class class_video extends Fragment {

    List<String> texte = new ArrayList<String>();
    List<String> Lien = new ArrayList<String>();
    List<String> image = new ArrayList<String>();
    private RecyclerView recyclerView;
    private View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        rootView = inflater.inflate(R.layout.content_main, container, false);


        try {
            URL url = new URL("http://addantibes.com/archives/category/audio/feed");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            // We will get the XML from an input stream
            xpp.setInput( getInputStream(url), "UTF_8");

            boolean insideItem = false;

            // Returns the type of current event: START_TAG, END_TAG, etc..
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = true;
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insideItem)
                            texte.add(xpp.nextText());  //extract the headline
                    } else if (xpp.getName().equalsIgnoreCase("link")) {
                        if (insideItem)
                            Lien.add(xpp.nextText());  //extract the link of article
                    } else if (xpp.getName().equalsIgnoreCase("post-thumbnail")) {
                            image.add( String.valueOf( xpp.nextText()));   //extract url
                    }

                }else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                    insideItem=false;
                }
                // On ajoute à la liste
                eventType = xpp.next(); //move to next element
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        String[] Array_texte = new String[texte.size()];
        Array_texte = texte.toArray(Array_texte);

        String[] Array_Lien = new String[Lien.size()];
        Array_Lien = Lien.toArray(Array_Lien);

        String[] Array_image = new String[image.size()];
        Array_image = image.toArray(Array_image);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        /*adapter mAdapter = new adapter(Array_texte, Array_image);
        mRecyclerView.setAdapter(mAdapter);*/

        //recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new adapter(Array_texte, Array_image));

        return rootView;
    }

    private InputStream getInputStream(URL url) throws IOException {
        return url.openConnection().getInputStream();
    }


}
