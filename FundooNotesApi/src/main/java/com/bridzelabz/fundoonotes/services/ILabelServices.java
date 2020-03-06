package com.bridzelabz.fundoonotes.services;

import com.bridzelabz.fundoonotes.dto.LabelDto;
import com.bridzelabz.fundoonotes.dto.LabelUpdate;

public interface ILabelServices {
	 boolean createLabel(LabelDto labelDto,String token);
	 boolean updateLabel(LabelUpdate labelUpdate,String token);
}
