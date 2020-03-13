package com.bridzelabz.fundoonotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.bridzelabz.fundoonotes.model.ProfileEntity;

public class ProfileServiceImp implements IProfileService{

	@Override
	public ProfileEntity storeobjectInS3(MultipartFile file, String filename, String contentType, String Token) {
		
		return null;
	}

	@Override
	public ProfileEntity updateObjectinS3(MultipartFile file, String filename, String contentType, String Token) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
