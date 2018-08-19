package com.example.dcc.bestlaptop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RanklistActivity extends AppCompatActivity{

    private DatabaseReference mDatabase;
    List<Laptop> laptops;
    List<Laptop> laptops2;
    private RecyclerView recyclerView;
    private LaptopAdapter mAdapter;
    int budget;
    public static Map<String, Integer> cpus = new HashMap<>();

    static{
        cpus.put("core i7 7th gen", 20234);
        cpus.put("core i7 5th gen", 13410);
        cpus.put("core i7 8th gen", 19204);
        cpus.put("core i7 6th gen", 17460);
        cpus.put("core i5 8th gen", 12121);
        cpus.put("core i7 4th gen", 10573);
        cpus.put("core i7 3rd gen", 8592);
        cpus.put("core i5 6th gen", 6678);
        cpus.put("core i5 7th gen", 8167);
        cpus.put("core i5 5th gen", 6600);
        cpus.put("core i5 4th gen", 6587);
        cpus.put("core i5 3rd gen", 6487);
        cpus.put("atom quad core x5", 1000);
        cpus.put("celeron dual core", 965);
        cpus.put("celeron dual core 4th gen", 1048);
        cpus.put("celeron quad core", 1100);
        cpus.put("pentium quad core", 1130);
        cpus.put("core i3 6th gen", 2265);
        cpus.put("core i3 5th gen", 1942);
        cpus.put("core i3 4th gen", 1722);
        cpus.put("core i3 3rd gen", 1521);
        cpus.put("amd quad core a6", 2000);
        cpus.put("core i3 7th gen", 2679);
        cpus.put("rockchip quad core", 1726);
        cpus.put("amd quad core a10", 2600);
        cpus.put("core m3 6th gen", 2500);
        cpus.put("core i3 8th gen", 3408);
        cpus.put("core m3 7th gen", 3512);
        cpus.put("amd quad core a4", 2463);
        cpus.put("amd quad core ryzen 5", 4695);
        cpus.put("amd quad core ryzen 7", 11343);
        cpus.put("amd quad core a12", 5000);
        cpus.put("core m", 4597);
        cpus.put("core m 5th gen", 5247);
        cpus.put("amd dual core a9", 4834);
        cpus.put("amd quad core a8", 7459);
        cpus.put("amd quad core pro a10", 4625);
        cpus.put("amd quad core pro a12", 6000);
        cpus.put("core i7 6700hq", 14670);
        cpus.put("amd quad core fx", 9783);
        cpus.put("core m5 6th gen", 13567);
        cpus.put("core m7 6th gen", 13667);
        cpus.put("atom quad core", 1068);
        cpus.put("ryzen 3 dual core", 1427);

//        cpus.put("")
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_list);

        laptops = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerView = (RecyclerView) findViewById(R.id.rankListRecyclerView);
        budget = getIntent().getIntExtra("budget", 0);

    }

    @Override
    protected void onStart() {
        super.onStart();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i =0;
                for(DataSnapshot laptopSnapshot : dataSnapshot.getChildren()){
                    try {
                        String cpu = laptopSnapshot.child("cpu").getValue().toString();
                        String display = laptopSnapshot.child("display").getValue().toString();
                        String model = laptopSnapshot.child("model").getValue().toString();
                        String price = laptopSnapshot.child("price").getValue().toString();
                        String ram = laptopSnapshot.child("ram").getValue().toString();
                        String storage = laptopSnapshot.child("storage").getValue().toString();
                        String graphics = "";
                        if (laptopSnapshot.hasChild("graphics")) {
                            graphics = laptopSnapshot.child("graphics").getValue().toString();
                        }
                        String nPrice = price.replaceAll("([A-Za-z .]+)", "");
                        int mPrice = Integer.parseInt(nPrice);

                        if((mPrice*2)<=budget)
                            laptops.add(new Laptop(cpu, display, model, mPrice*2, ram, storage, graphics));
                    }catch (Exception e){
                        Log.e("ID: ", laptopSnapshot.getKey());
                    }
                }

                Collections.sort(laptops, new Comparator<Laptop>() {
                    @Override
                    public int compare(Laptop laptop, Laptop t1) {
                        if(laptop.getCpu()
                                .toLowerCase()
                                .substring(laptop.getCpu().length()-7, laptop.getCpu().length())
                                .compareTo(t1.getCpu()
                                        .toLowerCase()
                                        .substring(t1.getCpu().length()-7, t1.getCpu().length()))>0){
                            return -1;
                        }
                        else if(laptop.getCpu()
                                .toLowerCase()
                                .substring(laptop.getCpu().length()-7, laptop.getCpu().length())
                                .compareTo(t1.getCpu()
                                        .toLowerCase()
                                        .substring(t1.getCpu().length()-7, t1.getCpu().length()))<0){
                            return 1;
                        }
                        return 0;
                    }
                });

                Collections.sort(laptops, new Comparator<Laptop>() {
                    @Override
                    public int compare(Laptop laptop, Laptop t1) {
                        try {
                            if (Integer.parseInt(laptop.getGraphics().trim().substring(0, 1)) > Integer.parseInt(t1.getGraphics().trim().substring(0, 1))) {
                                return -1;
                            } else if (Integer.parseInt(laptop.getGraphics().trim().substring(0, 1)) < Integer.parseInt(t1.getGraphics().trim().substring(0, 1))) {
                                return 1;
                            }
                            return 0;
                        }catch (Exception e){
                            System.out.println(laptop.getModel());
                            System.out.println(laptop.getGraphics());
                            System.out.println(t1.getModel());
                            System.out.println(t1.getGraphics());
                            return 0;
                        }
                    }
                });

                Collections.sort(laptops, new Comparator<Laptop>() {
                    @Override
                    public int compare(Laptop laptop, Laptop t1) {
                        if(Integer.parseInt(laptop.getRam().trim().substring(0, 1))>Integer.parseInt(t1.getRam().trim().substring(0, 1))){
                            return -1;
                        }
                        else if(Integer.parseInt(laptop.getRam().trim().substring(0, 1))<Integer.parseInt(t1.getRam().trim().substring(0, 1))){
                            return 1;
                        }
                        return 0;
                    }
                });


                Collections.sort(laptops, new Comparator<Laptop>() {
                    @Override
                    public int compare(Laptop laptop, Laptop t1) {
                        int size1 = laptop.getCpu().length()-8;
                        int size2 = t1.getCpu().length()-8;

                        if (cpus.get(laptop.getCpu()
                                .toLowerCase()
                                .replace("-", " ")
                                .replace(",", "")
                                .substring(0, size1))
                                > cpus.get(t1.getCpu()
                                .toLowerCase()
                                .replace("-", " ")
                                .replace(",", "")
                                .substring(0, size2))) {
                            return -1;
                        } else if (cpus.get(laptop.getCpu()
                                .toLowerCase()
                                .replace("-", " ")
                                .replace(",", "")
                                .substring(0, size1))
                                < cpus.get(t1.getCpu()
                                .toLowerCase()
                                .replace("-", " ")
                                .replace(",", "")
                                .substring(0, size2))) {
                            return 1;
                        }
                        return 0;

                    }
                });


//                Collections.sort(laptops, new Comparator<Laptop>() {
//                    @Override
//                    public int compare(Laptop laptop, Laptop t1) {
//                        if((laptop.getPrice()>t1.getPrice())){
//                            return -1;
//                        }
//                        else if((laptop.getPrice()<t1.getPrice())){
//                            return 1;
//                        }
//                        return 0;
//                    }
//                });

                mAdapter = new LaptopAdapter(laptops);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
