package com.example.ernest.legoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.thingworx.communications.client.ClientConfigurator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {



    HttpURLConnection httpURLConnection;
    TextView counterView;
    TextView redcounterView;
    TextView greecounterView;
    TextView bluecounterView;
    TextView stateView;
    CheckBox onlineCheckBox;
    CheckBox workingCheckBox;
    CheckBox invalidColorCheckBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        counterView = (TextView) findViewById(R.id.counterView);
        redcounterView = (TextView) findViewById(R.id.redView);
        greecounterView = (TextView) findViewById(R.id.greenView);
        bluecounterView = (TextView) findViewById(R.id.blueView);
        stateView=(TextView) findViewById(R.id.armState);
        onlineCheckBox=(CheckBox) findViewById(R.id.checkBoxOnline);
        workingCheckBox=(CheckBox) findViewById(R.id.checkBoxWorking);
        invalidColorCheckBox=(CheckBox) findViewById(R.id.checkBoxinvalidColor);


        //ThingWorx zapomniał o o certyfikatach
        HttpsTrustManager.allowAllSSL();

        counter();
        //redCounter();
        //greenCounter();
        //blueCounter();
    }
    public int toNumeric(String napis) {
        String value = napis;
        String intValue = value.replaceAll("[^0-9]", "");
        return Integer.parseInt(intValue);

    }
    public boolean flag(String napis){
        if(napis.charAt(0)=='f')
            return false;
        else
            return true;
    }
    private void counter() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                URL url = null;
                try {
                    url = new URL("https://pp-1901081737n9.devportal.ptc.io/Thingworx/Things/Production/Services/getCounterValue?appKey=ee20eacc-cf68-4770-866f-ea40846f25e5");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", "application/json");
                    //httpURLConnection.addRequestProperty("Accept", "application/json");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.connect();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
                    outputStreamWriter.write("");
                    outputStreamWriter.flush();


                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        stringBuffer.append(inputLine);
                    }
                    String result = stringBuffer.toString();


                    //counterValue.setText(result);
                    String[] tab = new String[2];
                    tab = result.split("<TD>", 2);
                    //char counter=tab[1].charAt(0);
                    final int counter = toNumeric(tab[1]);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            counterView.setText(String.valueOf(counter));
                        }
                    });
                    //outputStreamWriter.close();
                    bufferedReader.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    url = new URL("https://pp-1901081737n9.devportal.ptc.io/Thingworx/Things/Production/Properties/redCounter?appKey=ee20eacc-cf68-4770-866f-ea40846f25e5");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    //httpURLConnection.addRequestProperty("Content-Type", "application/json");
                    //httpURLConnection.addRequestProperty("Accept", "application/json");
                    //httpURLConnection.setDoOutput(true);
                    //httpURLConnection.connect();
                    //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
                    //outputStreamWriter.write("");
                    ///outputStreamWriter.flush();


                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        stringBuffer.append(inputLine);
                    }
                    String result = stringBuffer.toString();


                    //counterValue.setText(result);
                    String[] tab = new String[2];
                    tab = result.split("<TD>", 2);
                    //char counter=tab[1].charAt(0);
                    final int counter = toNumeric(tab[1]);
                    System.out.println(counter + "XDDDDDDDDD");
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            redcounterView.setText(String.valueOf(counter));
                        }
                    });
                    //outputStreamWriter.close();
                    bufferedReader.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    url = new URL("https://pp-1901081737n9.devportal.ptc.io/Thingworx/Things/Production/Properties/greenCounter?appKey=ee20eacc-cf68-4770-866f-ea40846f25e5");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    //httpURLConnection.addRequestProperty("Content-Type", "application/json");
                    //httpURLConnection.addRequestProperty("Accept", "application/json");
                    //httpURLConnection.setDoOutput(true);
                    // httpURLConnection.connect();


                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        stringBuffer.append(inputLine);
                    }
                    String result = stringBuffer.toString();


                    //counterValue.setText(result);
                    String[] tab = new String[2];
                    tab = result.split("<TD>", 2);
                    //char counter=tab[1].charAt(0);
                    final int counter = toNumeric(tab[1]);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            greecounterView.setText(String.valueOf(counter));
                        }
                    });
                    bufferedReader.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    url = new URL("https://pp-1901081737n9.devportal.ptc.io/Thingworx/Things/Production/Properties/blueCounter?appKey=ee20eacc-cf68-4770-866f-ea40846f25e5");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    //httpURLConnection.addRequestProperty("Content-Type", "application/json");
                    //httpURLConnection.addRequestProperty("Accept", "application/json");
                    //httpURLConnection.setDoOutput(true);
                    //httpURLConnection.connect();


                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        stringBuffer.append(inputLine);
                    }
                    String result = stringBuffer.toString();


                    //counterValue.setText(result);
                    String[] tab = new String[2];
                    tab = result.split("<TD>", 2);
                    //char counter=tab[1].charAt(0);
                    final int counter = toNumeric(tab[1]);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            bluecounterView.setText(String.valueOf(counter));
                        }
                    });

                    bufferedReader.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    url = new URL("https://pp-1901081737n9.devportal.ptc.io/Thingworx/Things/Arm1/Properties/state?appKey=ee20eacc-cf68-4770-866f-ea40846f25e5");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");


                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        stringBuffer.append(inputLine);
                    }
                    String result = stringBuffer.toString();


                    //counterValue.setText(result);
                    String[] tab = new String[2];
                    tab = result.split("<TD>", 2);
                    //char counter=tab[1].charAt(0);
                    final int counter = toNumeric(tab[1]);
                    System.out.println(counter);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            stateView.setText(String.valueOf(counter));
                        }
                    });
                    //outputStreamWriter.close();
                    bufferedReader.close();


                }catch (FileNotFoundException e){
                    try {
                       System.out.println(httpURLConnection.getResponseCode());
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
                        String inputLine;
                        StringBuffer stringBuffer = new StringBuffer();
                        while ((inputLine = bufferedReader.readLine()) != null) {
                            stringBuffer.append(inputLine);
                        }
                        System.out.println(stringBuffer.toString());
                    }catch (Exception g){
                        g.printStackTrace();
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                } try {
                    url = new URL("https://pp-1901081737n9.devportal.ptc.io/Thingworx/Things/Production/Properties/isWorkingFlag?appKey=ee20eacc-cf68-4770-866f-ea40846f25e5");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    //httpURLConnection.addRequestProperty("Content-Type", "application/json");
                    //httpURLConnection.addRequestProperty("Accept", "application/json");
                    //httpURLConnection.setDoOutput(true);
                    //httpURLConnection.connect();


                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        stringBuffer.append(inputLine);
                    }
                    String result = stringBuffer.toString();


                    //counterValue.setText(result);
                    String[] tab = new String[2];
                    tab = result.split("<TD>", 2);
                    //char counter=tab[1].charAt(0);
                    final boolean flag=flag(tab[1]);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            workingCheckBox.setChecked(flag);
                        }
                    });

                    bufferedReader.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    url = new URL("https://pp-1901081737n9.devportal.ptc.io/Thingworx/Things/Production/Properties/invalidColorFlag?appKey=ee20eacc-cf68-4770-866f-ea40846f25e5");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    //httpURLConnection.addRequestProperty("Content-Type", "application/json");
                    //httpURLConnection.addRequestProperty("Accept", "application/json");
                    //httpURLConnection.setDoOutput(true);
                    //httpURLConnection.connect();


                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        stringBuffer.append(inputLine);
                    }
                    String result = stringBuffer.toString();


                    //counterValue.setText(result);
                    String[] tab = new String[2];
                    tab = result.split("<TD>", 2);
                    //char counter=tab[1].charAt(0);
                    final boolean flag=flag(tab[1]);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            invalidColorCheckBox.setChecked(flag);
                        }
                    });

                    bufferedReader.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    url = new URL("https://pp-1901081737n9.devportal.ptc.io/Thingworx/Things/Production/Properties/isOnline?appKey=ee20eacc-cf68-4770-866f-ea40846f25e5");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    //httpURLConnection.addRequestProperty("Content-Type", "application/json");
                    //httpURLConnection.addRequestProperty("Accept", "application/json");
                    //httpURLConnection.setDoOutput(true);
                    //httpURLConnection.connect();


                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        stringBuffer.append(inputLine);
                    }
                    String result = stringBuffer.toString();


                    //counterValue.setText(result);
                    String[] tab = new String[2];
                    tab = result.split("<TD>", 2);
                    //char counter=tab[1].charAt(0);
                    final boolean flag=flag(tab[1]);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            onlineCheckBox.setChecked(flag);
                        }
                    });

                    bufferedReader.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }).start();
    }

    private void redCounter() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                URL url = null;
                try {
                    url = new URL("https://pp-1901081737n9.devportal.ptc.io/Thingworx/Things/Production/Properties/redCounter?appKey=ee20eacc-cf68-4770-866f-ea40846f25e5");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    //httpURLConnection.addRequestProperty("Content-Type", "application/json");
                    //httpURLConnection.addRequestProperty("Accept", "application/json");
                    //httpURLConnection.setDoOutput(true);
                    //httpURLConnection.connect();
                    //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
                    //outputStreamWriter.write("");
                    ///outputStreamWriter.flush();


                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        stringBuffer.append(inputLine);
                    }
                    String result = stringBuffer.toString();


                    //counterValue.setText(result);
                    String[] tab = new String[2];
                    tab = result.split("<TD>", 2);
                    //char counter=tab[1].charAt(0);
                    final int counter = toNumeric(tab[1]);
                    System.out.println(counter+"XDDDDDDDDD");
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            redcounterView.setText(String.valueOf(counter));
                        }
                    });
                    //outputStreamWriter.close();
                    bufferedReader.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void greenCounter() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                URL url = null;
                try {
                    url = new URL("https://pp-1901081737n9.devportal.ptc.io/Thingworx/Things/Production/Properties/greenCounter?appKey=ee20eacc-cf68-4770-866f-ea40846f25e5");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    //httpURLConnection.addRequestProperty("Content-Type", "application/json");
                    //httpURLConnection.addRequestProperty("Accept", "application/json");
                    //httpURLConnection.setDoOutput(true);
                   // httpURLConnection.connect();



                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        stringBuffer.append(inputLine);
                    }
                    String result = stringBuffer.toString();


                    //counterValue.setText(result);
                    String[] tab = new String[2];
                    tab = result.split("<TD>", 2);
                    //char counter=tab[1].charAt(0);
                    final int counter = toNumeric(tab[1]);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            greecounterView.setText(String.valueOf(counter));
                        }
                    });
                    bufferedReader.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
/*
    private void blueCounter() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                URL url = null;
                try {
                    url = new URL("https://pp-1901081737n9.devportal.ptc.io/Thingworx/Things/Production/Properties/blueCounter?appKey=ee20eacc-cf68-4770-866f-ea40846f25e5");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    //httpURLConnection.addRequestProperty("Content-Type", "application/json");
                    //httpURLConnection.addRequestProperty("Accept", "application/json");
                    //httpURLConnection.setDoOutput(true);
                    //httpURLConnection.connect();


                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        stringBuffer.append(inputLine);
                    }
                    String result = stringBuffer.toString();


                    //counterValue.setText(result);
                    String[] tab = new String[2];
                    tab = result.split("<TD>", 2);
                    //char counter=tab[1].charAt(0);
                    final int counter = toNumeric(tab[1]);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            bluecounterView.setText(String.valueOf(counter));
                        }
                    });

                    bufferedReader.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }*/
}
