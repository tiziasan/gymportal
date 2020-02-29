package it.univaq.disim.mwt.myunivaq.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.myunivaq.business.BusinessException;
import it.univaq.disim.mwt.myunivaq.business.UtenteService;
import it.univaq.disim.mwt.myunivaq.domain.Docente;
import it.univaq.disim.mwt.myunivaq.domain.Ruolo;
import it.univaq.disim.mwt.myunivaq.domain.Studente;
import it.univaq.disim.mwt.myunivaq.domain.Utente;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class JDBCUtenteServiceImpl implements UtenteService {
	
	private static final String FIND_USERNAME = "SELECT * FROM utenti u WHERE u.username = ?";
	private static final String FIND_USERNAME_ROLES = "SELECT r.* FROM ruoli_utenti ur, ruoli r WHERE ur.id_utente = ? AND ur.id_ruolo = r.id_ruolo";
	private static final String UPDATE_PROFILE = "UPDATE utenti SET email=?, codice_fiscale=? , data_nascita=?, telefono=? WHERE id_utente=?";

	@Autowired
	private DataSource dataSource;

	@Override
	public Utente findUtenteByUsername(String username) throws BusinessException {
		Utente utente = null;
		try (Connection con = dataSource.getConnection(); PreparedStatement st = con.prepareStatement(FIND_USERNAME);) {
			st.setString(1, username);
			try (ResultSet rs = st.executeQuery();) {
				if (rs.next()) {
					int tipologia = rs.getInt("tipologia_utente");
					switch (tipologia) {
					case 1:
						utente = new Docente();
						break;
					case 2:
						utente = new Studente();
						break;

					default:
						utente = new Utente();
						break;
					}
					utente.setId(rs.getLong("id_utente"));
					utente.setUsername(username);
					utente.setPassword(rs.getString("password"));
					utente.setCognome(rs.getString("cognome"));
					utente.setNome(rs.getString("nome"));
					utente.setEmail(rs.getString("email"));
					utente.setDataNascita(rs.getDate("data_nascita"));
					utente.setMatricola(rs.getString("matricola"));
					utente.setCodiceFiscale(rs.getString("codice_fiscale"));
					utente.setTelefono(rs.getString("telefono"));
					Set<Ruolo> ruoli = findRoles(utente.getId(), con);
					utente.setRuoli(ruoli);
				} else {
					log.error("errore nella find dell'utente" + username );
					throw new BusinessException();
				}

			}
		} catch (SQLException e) {
			log.error("findUtenteByUsername", e);
			throw new BusinessException("findUtenteByUsername", e);
		}
		return utente;
	}

	private Set<Ruolo> findRoles(Long idUtente, Connection con) throws BusinessException {
		Set<Ruolo> result = new HashSet<Ruolo>();
		try (PreparedStatement st = con.prepareStatement(FIND_USERNAME_ROLES);) {
			st.setLong(1, idUtente);
			try (ResultSet rs = st.executeQuery();) {
				while (rs.next()) {
					Ruolo ruolo = new Ruolo();
					ruolo.setId(rs.getLong("id_ruolo"));
					ruolo.setNome(rs.getString("nome"));
					ruolo.setDescrizione(rs.getString("descrizione"));
					result.add(ruolo);
				}

			}
		} catch (SQLException e) {
			log.error("findRoles", e);
			throw new BusinessException("findRoles", e);
		}
		return result;
	}

	@Override
	public void updateProfilo(Utente nuovoProfilo) throws BusinessException {

		try (Connection con = dataSource.getConnection();
				PreparedStatement st = con.prepareStatement(UPDATE_PROFILE);) {
			st.setString(1, nuovoProfilo.getEmail());
			st.setString(2, nuovoProfilo.getCodiceFiscale());
			st.setDate(3, new java.sql.Date(nuovoProfilo.getDataNascita().getTime()));
			st.setString(4, nuovoProfilo.getTelefono());
			st.setLong(5, nuovoProfilo.getId());
			st.executeUpdate();

		} catch (SQLException e) {
			log.error("updateProfilo", e);
			throw new BusinessException("updateProfilo", e);
		}

	}

}
