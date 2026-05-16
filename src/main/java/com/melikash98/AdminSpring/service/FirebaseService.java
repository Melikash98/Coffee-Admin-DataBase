package com.melikash98.AdminSpring.service;


import com.google.firebase.database.*;
import com.melikash98.AdminSpring.Model.AdminUser;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class FirebaseService {
    public AdminUser getAdminByUid(String uid) throws Exception {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("CoffeeShoppUsers")
                .child(uid);

        CompletableFuture<AdminUser> future = new CompletableFuture<>();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    AdminUser user = new AdminUser();
                    user.setUid(uid);
                    user.setEmail((String) snapshot.child("email").getValue());
                    user.setPassword((String) snapshot.child("password").getValue());
                    user.setOwnerName((String) snapshot.child("nameOwner").getValue());
                    user.setUserName((String) snapshot.child("userName").getValue());
                    user.setShoopName((String) snapshot.child("shopName").getValue());
                    user.setOwnerPhone((String) snapshot.child("ownerPhone").getValue());
                    user.setOwnerPhoto((String) snapshot.child("ownerPhoto").getValue());
                    future.complete(user);
                } else {
                    future.completeExceptionally(
                            new RuntimeException("User not found")
                    );
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeExceptionally(
                        new RuntimeException(error.getMessage())
                );
            }
        });

        return future.get();
    }
}
