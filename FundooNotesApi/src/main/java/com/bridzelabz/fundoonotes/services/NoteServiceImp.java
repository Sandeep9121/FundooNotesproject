package com.bridzelabz.fundoonotes.services;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridzelabz.fundoonotes.customexception.NoteNotFoundException;
import com.bridzelabz.fundoonotes.customexception.UserNotFoundException;
import com.bridzelabz.fundoonotes.dto.NoteDto;
import com.bridzelabz.fundoonotes.dto.NoteUpdate;
import com.bridzelabz.fundoonotes.dto.ReminderDto;
import com.bridzelabz.fundoonotes.model.NotesEntity;
import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.repository.INoteRepository;
import com.bridzelabz.fundoonotes.repository.IUsersRepository;
import com.bridzelabz.fundoonotes.utility.JWTGenerator;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoteServiceImp implements INoteServices {
	@Autowired
	private INoteRepository notesRepository;
	@Autowired
	private IUsersRepository usersRespository;
	
	private NotesEntity notes=new NotesEntity();

	@Autowired
	private JWTGenerator generateToken;
	

	@Transactional
	@Override
	public boolean createNote(NoteDto notesDto, String token) {
		Long userId = generateToken.parseJWTToken(token);
		log.info("----------------------ours-----------token " + token);
		Optional<UsersEntity> user = usersRespository.findById(userId);
		try {
			if (user != null) {
				BeanUtils.copyProperties(notesDto, notes);
				notes.setArchieved(false);
				notes.setColor("white");
				notes.setNotesCreatedDate(LocalDateTime.now());
				notes.setPinned(false);
				notes.setTrashed(false);
				notesRepository.createNote(notes);
			} else {
				throw new NoteNotFoundException("note is note present with given userId");
			}
		} catch (Exception e) {
			throw new UserNotFoundException("user is not present with the given id ");
		}
		return true;
	}

	@Transactional
	public boolean updateNote(NoteUpdate updateInfo, String token) {
		try {
		
			Long userId = generateToken.parseJWTToken(token);
			log.info("----------------------ours----urs userid " + userId);
			
			NotesEntity notes = notesRepository.findBynotesId(updateInfo.getNoteId());	
			if(notes!=null) {
				log.info("--------------------------------------note"+notes);
				notes.setNotesId(updateInfo.getNoteId());
				notes.setTitle(updateInfo.getTitle());
				notes.setDescription(updateInfo.getDescription());
				notes.setColor(updateInfo.getColor());
				notes.setPinned(updateInfo.isPinned());
				notes.setArchieved(updateInfo.isArchieved());
				notes.setTrashed(updateInfo.isTrashed());
				notes.setUpdateDate(LocalDateTime.now());
			}
			else {
				throw new NoteNotFoundException("note is note present with given token");
			}
		} 
		catch (Exception e) {
			throw new UserNotFoundException("user is not present with the given id ");
			}
		return true;
	}

	
	public int deleteNote(long notesId, String token) {
		NotesEntity notes =notesRepository.findBynotesId(notesId);
		if(notes!=null) {
		notes.setTrashed(!notes.isTrashed());// it will give false make it true 
		return notesRepository.deleteNote(notesId,notes);
		}
		else {
			throw new NoteNotFoundException("there is no notes please create");
			}
	}
	@Transactional
	public boolean archieveNote(long notesId, String token) {
		NotesEntity	notes =notesRepository.findBynotesId(notesId);
		if(notes!=null) {
		notes.setArchieved(!notes.isArchieved());
		notesRepository.createNote(notes);
		return true;
		}else {
		throw new NoteNotFoundException("there is no notes please create");
		}
	}
	@Transactional
	public boolean pinNote(long notesId, String token) {
		NotesEntity notes=notesRepository.findBynotesId(notesId);
		if(notes!=null) {
		notes.setPinned(!notes.isPinned());
		notesRepository.createNote(notes);
		return true;
		}
		else {
			throw new NoteNotFoundException("there is no notes please create");
			}
	}

	@Transactional
	public boolean addColor(long notesId, String token, String color) {
		Long userId = generateToken.parseJWTToken(token);
		log.info("----------------------ours----urs userid " + userId);
		NotesEntity notes=notesRepository.findBynotesId(notesId);
		if(notes!=null) {
		notes.setColor(color);
		notesRepository.createNote(notes);
		return true;
		}
		else {
			throw new NoteNotFoundException("there is no notes please create");
			}
	}

	@Transactional
	public boolean trashed(String token, long notesId) {
		if(notes!=null) {
		 if(notesRepository.setTrashed(token,notesId)) { 
			return true;}
		throw new NoteNotFoundException("there is no notes please create");
	}
		return true;
	}
	@Transactional
	public boolean restored(String token, long notesId) {
		Long userId = generateToken.parseJWTToken(token);
		if(notesRepository.setRestored(userId,notesId)) {
			return true;
		}
		return true;
	}

	@Transactional
	public boolean addReminder(String token, Long notesId,ReminderDto reminder) {
		NotesEntity notes=notesRepository.findBynotesId(notesId);
		 if(notes!=null) {
			 notes.setReminder(reminder.getReminder());
			 notesRepository.createNote(notes);
		 }
		 else {
			 throw new NoteNotFoundException("there is no notes on userId please create a note");
		 }
		return true;
	}

	@Transactional
	public boolean removeReminder(String token, Long notesId, ReminderDto reminder) {
		NotesEntity notes=notesRepository.findBynotesId(notesId);
		if(notes!=null) {
			notes.setReminder(null);
			notesRepository.createNote(notes);
		}
		 else {
			 throw new NoteNotFoundException("there is no notes on userId please create a note");
		 }
		return true ;
	}

}
