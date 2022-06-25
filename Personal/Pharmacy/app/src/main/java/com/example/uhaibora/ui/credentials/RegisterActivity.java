package com.example.uhaibora.ui.credentials;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uhaibora.R;
import com.example.uhaibora.databinding.ActivityRegisterBinding;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private String firstname,lastname, email, phone, password;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> country_codes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Registration");

        firstname=binding.txtFirstname.getText().toString();
        lastname=binding.txtLastName.getText().toString();
        email=binding.txtEmail.getText().toString();
        phone=binding.txtPhone.getText().toString();
        password=binding.txtPassword.getText().toString();

        binding.btnLogin.setOnClickListener(view -> {
            if (validateinput()){
                signup(firstname, lastname, email, phone, password);
                clearDetails();
            }
        });

        binding.textViewLogin.setOnClickListener(view -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));

        binding.btnClear.setOnClickListener(view -> clearDetails());

        /*country_codes=new ArrayList<>();

        arrayAdapter=new ArrayAdapter<String>(this, R.layout.dropdown_menu, country_codes);

        MaterialAutoCompleteTextView autoCompleteTextView=findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);*/

        // Create an ArrayAdapter using the string array and a default spinner
        /*ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.country_codes,
                        R.layout.dropdown_menu);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        binding.autoCompleteTextView.setAdapter(staticAdapter);*/

    }

    private void clearDetails() {
        binding.txtFirstname.setText("");
        binding.txtLastName.setText("");
        binding.txtPhone.setText("");
        binding.txtPassword.setText("");
    }

    private boolean validateinput() {
        if (TextUtils.isEmpty(firstname)) {
            binding.txtFirstname.setError("Please enter your first name");
            binding.txtFirstname.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(lastname)) {
            binding.txtLastName.setError("Please enter your last name");
            binding.txtLastName.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(email)) {
            binding.txtEmail.setError("Please enter your email");
            binding.txtEmail.requestFocus();
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.txtEmail.setError("Please enter a valid email");
            binding.txtEmail.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(phone)){
            binding.txtPhone.setError("Please enter phone");
            binding.txtPhone.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(password)){
            binding.txtPassword.setError("Please enter password");
            return false;
        }
        else if (password.length()<8){
            binding.txtPassword.setError("Password must contain more than 8 characters");
            return false;
        }

        return true;
    }

    private void signup(String firstname,String lastname, String email, String phone, String password) {
        // url to post our data
        String url = "http://192.168.100.53/pharmacy/signup.php";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            Log.e("TAG", "RESPONSE IS " + response);

            try {
                JSONObject jsonObject = new JSONObject(response);
                // on below line we are displaying a success toast message.
                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // and setting data to edit text as empty
            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        }, error -> {
            // method to handle errors.
            Toast.makeText(getApplicationContext(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("email", email);
                params.put("phone", phone);
                params.put("password", password);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }
}