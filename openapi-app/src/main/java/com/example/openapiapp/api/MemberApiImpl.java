package com.example.openapiapp.api;

import com.example.demo.api.MemberApi;
import com.example.demo.api.model.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class MemberApiImpl implements MemberApi {

    @Override
    public ResponseEntity<Member> getMember(Long id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }



}
