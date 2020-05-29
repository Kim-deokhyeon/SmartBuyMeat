package navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartbuymeat2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import navigation.background.Background;
import navigation.background.Background1;
import navigation.background.Background2;

public class InformFragment extends Fragment {

    View fragmentView;

    //asynctask
    Background task;
    Background1 Gu_task;
    Background2 Gun_task;

    JSONArray jsonArray;

    JSONObject city;
    JSONObject gu;
    JSONObject n_x,n_y;

    //spinner
    ArrayList<String> arrayList, arrayList2, arrayList3;
    ArrayAdapter<String> arrayAdapter, arrayAdapter2, arrayAdapter3;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_inform, container, false);

        final Spinner sp1 = (Spinner) fragmentView.findViewById(R.id.spinner1);
        final Spinner sp2 = (Spinner) fragmentView.findViewById(R.id.spinner2);
        final Spinner sp3 = (Spinner) fragmentView.findViewById(R.id.spinner3);

        final String url = "http://www.kma.go.kr/DFSROOT/POINT/DATA/top.json.txt";

        city = new JSONObject();
        gu = new JSONObject();
        task = new Background();



        try{
            jsonArray = task.execute(url).get();
            arrayList = new ArrayList<>();

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                arrayList.add(jsonObject.getString("value"));

                city.put(jsonObject.getString("value"),jsonObject.getString("code"));
            }

            arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item,arrayList);

            sp1.setAdapter(arrayAdapter);
        }
        catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            JSONArray Gu_jsonArray;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                System.out.println("#####" + s);
                Gu_task = new Background1();
                try {
                    Gu_jsonArray = Gu_task.execute("http://www.kma.go.kr/DFSROOT/POINT/DATA/mdl." + city.getString(s) + ".json.txt").get();
                    arrayList2 = new ArrayList<>();

                    for (int i = 0; i < Gu_jsonArray.length(); i++) {
                        JSONObject jsonObject = Gu_jsonArray.getJSONObject(i);
                        arrayList2.add(jsonObject.getString("value"));

                        gu.put(jsonObject.getString("value"), jsonObject.getString("code"));
                    }

                    arrayAdapter2 = new ArrayAdapter<>(getActivity().getApplicationContext(),
                            android.R.layout.simple_spinner_dropdown_item, arrayList2);

                    sp2.setAdapter(arrayAdapter2);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            JSONArray Gun_jsonArray;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String s = parent.getItemAtPosition(position).toString();

                Gun_task = new Background2();
                //위치의 좌표
                n_x = new JSONObject();
                n_y = new JSONObject();

                try {
                    Gun_jsonArray = Gun_task.execute("http://www.kma.go.kr/DFSROOT/POINT/DATA/leaf." + gu.getString(s) + ".json.txt").get();
                    arrayList3 = new ArrayList<>();

                    for (int i = 0; i < Gun_jsonArray.length(); i++) {
                        JSONObject jsonObject = Gun_jsonArray.getJSONObject(i);
                        arrayList3.add(jsonObject.getString("value"));

                        n_x.put(jsonObject.getString("value"), jsonObject.getString("x"));
                        n_y.put(jsonObject.getString("value"), jsonObject.getString("y"));
                    }

                    arrayAdapter3 = new ArrayAdapter<>(getActivity().getApplicationContext(),
                            android.R.layout.simple_spinner_dropdown_item, arrayList3);

                    sp3.setAdapter(arrayAdapter3);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return fragmentView;
    }




}