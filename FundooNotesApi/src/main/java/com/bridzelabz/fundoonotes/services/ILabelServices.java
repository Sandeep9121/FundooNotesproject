package com.bridzelabz.fundoonotes.services;

import com.bridzelabz.fundoonotes.dto.LabelDto;

public interface ILabelServices {
	 boolean createLabel(LabelDto label,String token);
}
