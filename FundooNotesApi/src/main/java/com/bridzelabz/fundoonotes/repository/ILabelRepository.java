package com.bridzelabz.fundoonotes.repository;

import com.bridzelabz.fundoonotes.model.Label;

public interface ILabelRepository {
   Label saveLabel(Label label);
   Label fetchLabelById(long labelId);
}
