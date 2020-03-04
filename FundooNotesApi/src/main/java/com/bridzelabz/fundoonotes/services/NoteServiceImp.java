package com.bridzelabz.fundoonotes.services;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridzelabz.fundoonotes.customexception.NoteNotFoundException;
import com.bridzelabz.fundoonotes.customexception.UserException;
import com.bridzelabz.fundoonotes.dto.NoteDto;
import com.bridzelabz.fundoonotes.dto.NoteUpdate;
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
	
	private NotesEntity note=new NotesEntity();
	
	@Autowired
	private NoteDto noteDto;
	
	@Autowired
	private JWTGenerator generateToken;
	@Autowired
	private UsersEntity user;

	@Transactional
	@Override
	public boolean createNote(NoteDto noteDto, String token) {
		Long userId = generateToken.parseJWTToken(token);
		log.info("----------------------ours-----------token " + token);
		Optional<UsersEntity> user = usersRespository.findById(userId);
		try {
			if (user != null) {
				BeanUtils.copyProperties(noteDto, note);
				note.setArchieved(false);
				note.setColor("white");
				note.setNotesCreatedDate(LocalDateTime.now());
				note.setPinned(false);
				note.setTrashed(false);
				notesRepository.createNote(note);
			} else {
				throw new NoteNotFoundException("note is note present with given userId");
			}
		} catch (Exception e) {
			throw new UserException("user is not present with the given id ");
		}
		return true;
	}

	@Transactional
	public boolean updateNote(NoteUpdate updateInfo, String token) {
		try {
			log.info("----------------------ours-----------token " + token);
			NotesEntity note = notesRepository.findBynotesId(updateInfo.getNoteId());	
			if(note!=null) {
				log.info("--------------------------------------note"+note);
				note.setNotesId(updateInfo.getNoteId());
				note.setTitle(updateInfo.getTitle());
				note.setDescription(updateInfo.getDescription());
				note.setColor(updateInfo.getColor());
				note.setPinned(updateInfo.isPinned());
				note.setArchieved(updateInfo.isArchieved());
				note.setTrashed(updateInfo.isTrashed());
				note.setUpdateDate(LocalDateTime.now());
			}
			else {
				throw new NoteNotFoundException("note is note present with given token");
			}
		} 
		catch (Exception e) {
			throw new UserException("user is not present with the given id ");
			}
		return true;
	}

	
	public int deleteNote(long notesId, String token) {
		NotesEntity note =notesRepository.findBynotesId(notesId);
		note.setTrashed(!note.isTrashed());// it will give false make it true 
		return notesRepository.deleteNote(notesId,note);
		}

	@Transactional
	public boolean archieveNote(long notesId, String token) {
		NotesEntity	note =notesRepository.findBynotesId(notesId);
		note.setArchieved(!note.isArchieved());
		notesRepository.createNote(note);
		return true;
	}

	@Override
	public boolean pinNote(long notesId, String token) {
		NotesEntity note=notesRepository.findBynotesId(notesId);
		note.setPinned(!note.isPinned());
		notesRepository.createNote(note);
		return true;
	}

}
