package com.bridzelabz.fundoonotes.services;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridzelabz.fundoonotes.customexception.LabelExitsException;
import com.bridzelabz.fundoonotes.customexception.LabelNotFoundException;
import com.bridzelabz.fundoonotes.customexception.UserNotFoundException;
import com.bridzelabz.fundoonotes.dto.LabelDto;
import com.bridzelabz.fundoonotes.dto.LabelUpdate;
import com.bridzelabz.fundoonotes.model.Label;
import com.bridzelabz.fundoonotes.model.NotesEntity;
import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.repository.ILabelRepository;
import com.bridzelabz.fundoonotes.repository.INoteRepository;
import com.bridzelabz.fundoonotes.repository.LabelRepository;
import com.bridzelabz.fundoonotes.repository.UsersRepository;
import com.bridzelabz.fundoonotes.utility.JWTGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LabelServiceImp implements ILabelServices {
	@Autowired
	private JWTGenerator generateToken;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
     private ILabelRepository iLabelRepository;
    @Autowired
    private INoteRepository notesRepository;

	public boolean createLabel(LabelDto labelDto,String token) {
      long userId=generateToken.parseJWTToken(token);
      log.info("----------------------------------------------------------token="+token);
        UsersEntity user=usersRepository.getusersByid(userId);
        if(user!=null) {
        	Label label=new Label();
        	Label label1=iLabelRepository.fetchLabel(userId,labelDto.getName());
        	if(label1==null) {
        	BeanUtils.copyProperties(labelDto, label);
        	label.setUserId(user.getUserId());
        	labelRepository.saveLabel(label);
        	}else {
        	throw new LabelExitsException("already label exits");
        	}
        }else {
        	throw new UserNotFoundException("there is no user found");
        }
		return true;
	}


	public boolean updateLabel(LabelUpdate labelUpdate, String token) {
		Long userId=generateToken.parseJWTToken(token);
		UsersEntity user=usersRepository.getusersByid(userId);
		  if(user!=null) {
			  Label label=labelRepository.fetchLabelById(labelUpdate.getLabelId());
			  if(label!=null) {
				  label.setName(labelUpdate.getLabelName());
				  labelRepository.saveLabel(label);
				  return true;
			  }else {
			  throw new LabelNotFoundException("there is no label with this user id");
		  }	}
		  return false;
		  }


	@Override
	public boolean addLabel(Long labelId, long notesId, String token) {
	
		return false;
	}


	@Override
	public boolean removeLabel(Long labelId, Long notesId, String token) {
		Long userId=generateToken.parseJWTToken(token);
UsersEntity user=usersRepository.getusersByid(userId);
if(user!=null) {
		NotesEntity note=notesRepository.findBynotesId(notesId);
	
		Label label=labelRepository.fetchLabelById(labelId);
		if(label!=null) {
		note.getList().remove(label);
		labelRepository.saveLabel(label);
		return true;
		}
}else {
	throw new UserNotFoundException("user is not availabe");
}
return false;
		
	}



	/*public boolean addLabel(Long labelId, long notesId, String token) {
	     NotesEntity note=notesRepository.findBynotesId(notesId);
	     log.info("----------notes-------------------id="+notesId);
	     Label label=labelRepository.fetchLabelById(labelId);
	   //  label.
		return false;
	}*/

	
	
}
