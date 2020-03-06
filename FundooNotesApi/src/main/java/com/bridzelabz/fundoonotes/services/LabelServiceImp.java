package com.bridzelabz.fundoonotes.services;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridzelabz.fundoonotes.customexception.LabelNotFoundException;
import com.bridzelabz.fundoonotes.customexception.UserNotFoundException;
import com.bridzelabz.fundoonotes.dto.LabelDto;
import com.bridzelabz.fundoonotes.dto.LabelUpdate;
import com.bridzelabz.fundoonotes.model.Label;
import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.repository.LabelRepository;
import com.bridzelabz.fundoonotes.repository.UsersRepository;
import com.bridzelabz.fundoonotes.utility.JWTGenerator;


@Service
public class LabelServiceImp implements ILabelServices {
	@Autowired
	private JWTGenerator generateToken;
    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private LabelRepository labelRepository;


	public boolean createLabel(LabelDto labelDto,String token) {
      long id=generateToken.parseJWTToken(token);
        UsersEntity user=usersRepository.getusersByid(id);
        if(user!=null) {
        	Label label=new Label();
        	BeanUtils.copyProperties(labelDto, label);
        	label.setUserId(user.getUserId());
        	labelRepository.saveLabel(label);
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
			  }
			  throw new LabelNotFoundException("there is no label with this user id");
		  }
		return true;
	}

}
