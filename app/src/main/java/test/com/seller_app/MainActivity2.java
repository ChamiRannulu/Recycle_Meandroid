package test.com.seller_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity2.this, android.R.layout.simple_list_item_1, arrayList);

        listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = arrayList.get(i);
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("Name", name);
                startActivity(intent);
                finish();
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Data");
//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                for(DataSnapshot ds : snapshot.getChildren()) {
//                    String name = ds.child("name").getValue(String.class);
//                    Log.d("TAG", " / " + name);
////                    arrayList.add(name);
////                    arrayAdapter.notifyDataSetChanged();
////                    Log.d("TAG", address + " / " + name);
//                }
////                String value = snapshot.getValue(String.class);
////                arrayList.add(value);
////                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //String email = ds.child("email").getValue(String.class);
                    String name = ds.child("name").getValue(String.class);
                    arrayList.add(name);
                    arrayAdapter.notifyDataSetChanged();
                    Log.d("TAG", " / " + name);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        databaseReference.addListenerForSingleValueEvent(eventListener);
    }
}