package com.example.tp4.utils;

import com.example.tp4.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<User> hasName(String name){
        return (root, query, criteriaBuilder) ->
                name == null ? criteriaBuilder.isTrue(criteriaBuilder.literal(true))
                        : criteriaBuilder.equal(root.get("name"), name);
    }
    public static Specification<User> hasAddress(String address){
        return (root, query, criteriaBuilder) ->
                address == null ? criteriaBuilder.isTrue(criteriaBuilder.literal(true))
                        : criteriaBuilder.equal(root.get("address"), address);
    }
    public static Specification<User> hasUsertype(UserType userType){
        return (root, query, criteriaBuilder) ->
                userType == null ? criteriaBuilder.isTrue(criteriaBuilder.literal(true))
                        : criteriaBuilder.equal(root.get("userType"), userType);
    }
}
