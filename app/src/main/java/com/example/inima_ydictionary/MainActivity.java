package com.example.inima_ydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> Meanings,Examples,Synonyms,Antonyms;
    ArrayList<JSONObject> ExtraObjects;

    EditText SearchBox;
    TextView WordText,DefinitionText,ExampleText,SynonymsText,AntonymsText,LatinText;
    ImageButton VoiceMod;
    ImageView SearchButton;
    ScrollView ScrollLayout;

    String Queen="yamini";
    String Queen2="yaminisri";
    String Queen3="rajulapatiyamini";
    String Queen4="rajulapatiyaminisri";
    String Queen5="yaminisrirajulapati";
    String Queen6="yaminirajulapati";


    String King ="sarath";
    String King1 ="sarathkumar";
    String King2 ="whitechuha";
    String King3 ="webmaster";


    LinearLayout Wordlayout,Textlayout,Definitionlayout,Examplelayout,Synonymslayout,Antonymslayout,BackArrow;

    private MediaPlayer mediaPlayer;

    public String getVoiceUrl() {
        return VoiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        VoiceUrl = voiceUrl;
    }

    String VoiceUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Meanings=new ArrayList<>();
        Examples=new ArrayList<>();
        ExtraObjects=new ArrayList<>();
        Synonyms=new ArrayList<>();
        Antonyms=new ArrayList<>();

        initViews();

        SearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // You can perform search functionality here using the user's input
                ScrollLayout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        SearchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    OnSearch();
                    InputMethodManager imm = (InputMethodManager) getSystemService(MainActivity.this.INPUT_METHOD_SERVICE);
                    // Hide the keyboard
                    imm.hideSoftInputFromWindow(SearchBox.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnSearch();
                InputMethodManager imm = (InputMethodManager) getSystemService(MainActivity.this.INPUT_METHOD_SERVICE);
                // Hide the keyboard
                imm.hideSoftInputFromWindow(SearchBox.getWindowToken(), 0);
            }
        });

        VoiceMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(getVoiceUrl());
            }
        });

        BackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void initViews(){
        SearchBox=findViewById(R.id.word_search);
        WordText=findViewById(R.id.word_txt);
        VoiceMod=findViewById(R.id.voice);
        DefinitionText=findViewById(R.id.definition_txt);
        SearchButton=findViewById(R.id.Dsearch_button);
        ExampleText=findViewById(R.id.exaple_txt);
        SynonymsText=findViewById(R.id.synonyms_txt);
        AntonymsText=findViewById(R.id.antonyms_txt);
        LatinText=findViewById(R.id.latin_txt);
        ScrollLayout=findViewById(R.id.scroll_view);

        Wordlayout=findViewById(R.id.Wordlayout);
        Textlayout=findViewById(R.id.Textlayout);
        Definitionlayout=findViewById(R.id.Definitionlayout);
        Examplelayout=findViewById(R.id.Examplelayout);
        Synonymslayout=findViewById(R.id.Synonymslayout);
        Antonymslayout=findViewById(R.id.Antonymslayout);
        BackArrow=findViewById(R.id.back_button);
    }
    String url = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    OkHttpClient client = new OkHttpClient();

    private void OnSearch(){
        if (!SearchBox.getText().toString().equals("")){
            //  WordText.setText("Loading...");

            String text=SearchBox.getText().toString().toLowerCase().replaceAll("\\s","");

            if (text.equals(Queen)||text.equals(Queen2)||text.equals(Queen3)||text.equals(Queen4)||text.equals(Queen5)||text.equals(Queen6)){

                VoiceMod.setVisibility(View.VISIBLE);
                Textlayout.setVisibility(View.VISIBLE);
                Definitionlayout.setVisibility(View.VISIBLE);
                Examplelayout.setVisibility(View.VISIBLE);
                Synonymslayout.setVisibility(View.VISIBLE);
                Antonymslayout.setVisibility(View.VISIBLE);
                ScrollLayout.setVisibility(View.VISIBLE);
                VoiceMod.setVisibility(View.GONE);

                WordText.setText(SearchBox.getText().toString().toUpperCase());
                DefinitionText.setText("She's a cute girl born in \"Vuyyuru\" village.");
                ExampleText.setText("1. Yamini draws well.\n2.Yamini is an Artist.");
                SynonymsText.setText("Yamini sri");
                AntonymsText.setText("⅄ɐɯıuı");
                LatinText.setText("ʕ•ﻌ•ʔ");

            }
            else if (text.equals(King)||text.equals(King1)||text.equals(King2)){

                VoiceMod.setVisibility(View.VISIBLE);
                Textlayout.setVisibility(View.VISIBLE);
                Definitionlayout.setVisibility(View.VISIBLE);
                Examplelayout.setVisibility(View.VISIBLE);
                Synonymslayout.setVisibility(View.VISIBLE);
                Antonymslayout.setVisibility(View.VISIBLE);
                ScrollLayout.setVisibility(View.VISIBLE);
                VoiceMod.setVisibility(View.GONE);

                WordText.setText(SearchBox.getText().toString().toUpperCase());
                DefinitionText.setText("1. He's from \"Ongole\".\n\n2. He's also known as Webmaster.");
                ExampleText.setText("1. Sarath tried to call Yamini.\n\n2.Sarath says sorry to Yamini, becuase he's late yesterday.");
                SynonymsText.setText("Sarath Kumar, Sarath, Webmaster");
                AntonymsText.setText("WhiteChuha");
                LatinText.setText("|/\\|πi†€©πuha");

            }

            else if (text.equals(King3)){

                VoiceMod.setVisibility(View.VISIBLE);
                Textlayout.setVisibility(View.VISIBLE);
                Definitionlayout.setVisibility(View.VISIBLE);
                Examplelayout.setVisibility(View.VISIBLE);
                Synonymslayout.setVisibility(View.VISIBLE);
                Antonymslayout.setVisibility(View.VISIBLE);
                ScrollLayout.setVisibility(View.VISIBLE);
                VoiceMod.setVisibility(View.GONE);

                WordText.setText(SearchBox.getText().toString().toUpperCase());
                DefinitionText.setText("1. He's from \"Ongole\".\n\n2. He's also known as Sarath.");
                ExampleText.setText("1. Sarath tried to call Yamini.\n\n2. Sarath says sorry to Yamini, becuase he's late yesterday.");
                SynonymsText.setText("Sarath Kumar, Sarath, Webmaster");
                AntonymsText.setText("WhiteChuha");
                LatinText.setText("|/\\|πi†€©πuha");

            }

            else {
                SetTextLoading();
                Request request = new Request.Builder()
                        .url(url+SearchBox.getText().toString())
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // Handle the API error here
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responseBody = response.body().string();
                            try {
                                JSONArray jsonArray = new JSONArray(responseBody);
                                // Handle the API response here

                                JSONObject jsonObject = jsonArray.getJSONObject(0);

                                for (int i=0;i<jsonArray.length();i++){
                                    ExtraObjects.add(jsonArray.getJSONObject(i));



                                    for (int j=0;j<5;j++){
                                        Meanings.add(WordMeanings(jsonArray.getJSONObject(i),j));
                                        Examples.add(WordExamples(jsonArray.getJSONObject(i),j));
                                        Synonyms.add(WordSynonyms(jsonArray.getJSONObject(i),j));
                                        Antonyms.add(WordAntonyms(jsonArray.getJSONObject(i),j));
                                    }

                                }

                                String wordtext=jsonObject.getString("word");
                                String latintext=LatinText(jsonObject);


                            /*for (int i=0;i<3;i++){
                                Meanings.add(WordMeaning(jsonObject,i));
                                Examples.add(WordExamples(jsonObject,i));
                            }*/

                                // setVoiceUrl(AudioUrl(jsonObject));
                                String adioUrl=AudioUrl(jsonObject);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Update UI components here
                                        if (!adioUrl.equals("")){
                                            VoiceMod.setVisibility(View.VISIBLE);
                                        }else {VoiceMod.setVisibility(View.INVISIBLE);}
                                        setVoiceUrl(adioUrl);
                                        WordText.setText(wordtext.toUpperCase());
//                                    DefinitionText.setText("1. "+meaning1+"\n2. "+meaning2+"\n3. "+meaning3);

                                        String Constantdef="";
                                        String Constantex="";
                                        String Constantsyn="";
                                        String Constantan="";
                                        int count1=1,count2=1;

                                        for (int i=0;i<Meanings.size();i++){
                                            if (!Meanings.get(i).equals("")){
                                                Constantdef=Constantdef+count1+". "+Meanings.get(i)+"\n\n";
                                                count1++;
                                            }

                                            if (!Constantdef.equals("")){
                                                DefinitionText.setText(Constantdef);
                                                Definitionlayout.setVisibility(View.VISIBLE);
                                            }else {
                                                Definitionlayout.setVisibility(View.GONE);
                                            }

                                        }

                                        for (int i=0;i<Examples.size();i++){
                                            if (!Examples.get(i).equals("")){
                                                Constantex=Constantex+count2+". "+Examples.get(i)+"\n\n";
                                                count2++;
                                            }
                                            if (!Constantex.equals("")){
                                                ExampleText.setText(Constantex);
                                                Examplelayout.setVisibility(View.VISIBLE);
                                            }else {
                                                Examplelayout.setVisibility(View.GONE);
                                            }
                                        }

                                        for (int i=0;i<Synonyms.size();i++){
                                            if (!Synonyms.get(i).equals("")){
                                                Constantsyn=Constantsyn+Synonyms.get(i)+"\n";
                                            }

                                            if (!Constantsyn.equals("")){
                                                SynonymsText.setText(Constantsyn);
                                                Synonymslayout.setVisibility(View.VISIBLE);
                                            }else {
                                                Synonymslayout.setVisibility(View.GONE);
                                            }
                                        }
                                        for (int i=0;i<Antonyms.size();i++){
                                            if (!Antonyms.get(i).equals("")){
                                                Constantan=Constantan+Antonyms.get(i)+"\n";
                                            }

                                            if (!Constantan.equals("")){
                                                AntonymsText.setText(Constantan);
                                                Antonymslayout.setVisibility(View.VISIBLE);
                                            }else {
                                                Antonymslayout.setVisibility(View.GONE);
                                            }
                                        }

                                        if (!latintext.equals("")){
                                            LatinText.setText(latintext);
                                            Textlayout.setVisibility(View.VISIBLE);
                                        }else {
                                            Textlayout.setVisibility(View.GONE);
                                        }

                                        Meanings.clear();
                                        Examples.clear();
                                        Synonyms.clear();
                                        Antonyms.clear();
                                        ExtraObjects.clear();

                                    }
                                });


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            // SearchBox.setText("");
                        } else {
                            // Handle the API error here
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Update UI components here
                                    WordText.setText("Check the spelling and try again");
                                }
                            });
                        }
                    }
                });
            }

        }
        else {
            Toast.makeText(MainActivity.this, "Please enter a word", Toast.LENGTH_SHORT).show();

        }
    }

    private void SetTextLoading() {

        WordText.setText("Loading...");

        VoiceMod.setVisibility(View.INVISIBLE);
        Textlayout.setVisibility(View.INVISIBLE);
        Definitionlayout.setVisibility(View.INVISIBLE);
        Examplelayout.setVisibility(View.INVISIBLE);
        Synonymslayout.setVisibility(View.INVISIBLE);
        Antonymslayout.setVisibility(View.INVISIBLE);
        ScrollLayout.setVisibility(View.VISIBLE);
    }

    public String AudioUrl(JSONObject jsonObject){
        String audiourl="";
        try {
            JSONArray phonetics = jsonObject.getJSONArray("phonetics");
            JSONObject meaning = phonetics.getJSONObject(0);
            audiourl = meaning.getString("audio");
            for (int i=0;i<6;i++){
                if (audiourl.equals("")){
                    audiourl=phonetics.getJSONObject(i).getString("audio");
                }
                else break;
            }
            // JSONObject definition = definitions.getJSONObject(0);
            // meaningText = definition.getString("definition");
            return audiourl;
        }catch (Exception e){
            return audiourl;
        }


    }

    public String LatinText(JSONObject jsonObject){
        String text="";
        try {
            JSONArray phonetics = jsonObject.getJSONArray("phonetics");
            JSONObject meaning = phonetics.getJSONObject(0);
            text = meaning.getString("text");
            for (int i=0;i<6;i++){
                if (text.equals("")){
                    text=phonetics.getJSONObject(i).getString("text");
                }
                else break;
            }

            return text;
        }catch (Exception e){

            return text;
        }


    }

    public String WordMeanings(JSONObject jsonObject, int i){

        String meaningText="";
        try {
            JSONArray meanings = jsonObject.getJSONArray("meanings");
            JSONObject meaning = meanings.getJSONObject(i);
            JSONArray definitions = meaning.getJSONArray("definitions");
            JSONObject definition = definitions.getJSONObject(0);
            meaningText = definition.getString("definition");
            String examples = definition.getString("example");
            return meaningText;
        }catch (Exception e){
            return meaningText;
        }
    }

    public String WordExamples(JSONObject jsonObject, int i){

        String examples="";
        try {
            JSONArray meanings = jsonObject.getJSONArray("meanings");
            JSONObject meaning = meanings.getJSONObject(i);
            JSONArray definitions = meaning.getJSONArray("definitions");
            JSONObject definition = definitions.getJSONObject(0);
            examples = definition.getString("example");
            return examples;
        }catch (Exception e){
            return examples;
        }
    }

    public String WordSynonyms(JSONObject jsonObject, int i){

        String synonym="";
        String finalSynonim="";
        try {
            JSONArray meanings = jsonObject.getJSONArray("meanings");
            JSONObject meaning = meanings.getJSONObject(i);
            JSONArray synonyms = meaning.getJSONArray("synonyms");
            for (int j=0;j<synonyms.length();j++){
                synonym = synonyms.getString(j);

                finalSynonim=finalSynonim+synonym+", ";

            }

            return finalSynonim;
        }catch (Exception e){

            return finalSynonim;
        }
    }

    public String WordAntonyms(JSONObject jsonObject, int i){

        String antonym="";
        String finalSynonim="";
        try {
            JSONArray meanings = jsonObject.getJSONArray("meanings");
            JSONObject meaning = meanings.getJSONObject(i);
            JSONArray antonyms = meaning.getJSONArray("antonyms");
            for (int j=0;j<antonyms.length();j++){
                antonym = antonyms.getString(j);
                if (!antonym.equals("")){
                    finalSynonim=finalSynonim+antonym+", ";
                }
            }

            return finalSynonim;
        }catch (Exception e){
            return finalSynonim;
        }
    }


    public void playAudio(String url) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}