package com.bridzelabz.fundoonotes.services;

import com.bridzelabz.fundoonotes.dto.LabelDto;
import com.bridzelabz.fundoonotes.dto.LabelUpdate;

public interface ILabelServices {
	 boolean createLabel(LabelDto labelDto,String token);
	 boolean updateLabel(LabelUpdate labelUpdate,String token);
	 boolean addLabel(Long labelId, long notesId, String token);
	 boolean removeLabel(Long labelId,Long notesId,String token);
	 
}
