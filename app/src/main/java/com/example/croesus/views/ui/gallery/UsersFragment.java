package com.example.croesus.views.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.croesus.R;
import com.example.croesus.model.entities.User;

import java.util.List;

public class UsersFragment extends Fragment {

    private UserViewModel userViewModel;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userViewModel =
                ViewModelProviders.of(this).get(UserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_user, container, false);


        final EditText txtFirstName = root.findViewById(R.id.txtFirstName);
        final EditText txtLastName = root.findViewById(R.id.txtLastName);
        final EditText txtIdNumber = root.findViewById(R.id.txtRegNumber);
        final Button btnSave = root.findViewById(R.id.btnSaveUser);


        userViewModel.getUsers().observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {

                user = users.get(0);

                txtFirstName.setText(user.getFirstName());
                txtLastName.setText(user.getLastName());
                txtIdNumber.setText(String.valueOf(user.getRegistrationNumber()));
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get data from screen
                String firstName = txtFirstName.getText().toString();
                String lastName = txtLastName.getText().toString();
                int regNumber = Integer.parseInt(txtIdNumber.getText().toString());

                // save user
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setRegistrationNumber(regNumber);
                userViewModel.update(user);

                navigate(v);
            }
        });

        return root;
    }

    private void navigate(View view) {
        Toast.makeText(getContext(), "User Updated", Toast.LENGTH_SHORT).show();

//        Navigation.findNavController(view).navigate(R.id.open_survey_screen);
    }
}