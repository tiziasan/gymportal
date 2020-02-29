package it.univaq.disim.mwt.myunivaq.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.disim.mwt.myunivaq.business.BusinessException;
import it.univaq.disim.mwt.myunivaq.business.RequestGrid;
import it.univaq.disim.mwt.myunivaq.business.ResponseGrid;
import it.univaq.disim.mwt.myunivaq.business.SSDService;
import it.univaq.disim.mwt.myunivaq.domain.AreaSSD;
import it.univaq.disim.mwt.myunivaq.domain.SettoreScientificoDisciplinare;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class JDBCSSDServiceImpl implements SSDService {


	private static final String FIND_AREE_SSD = "SELECT assd.* FROM aree_ssd assd order by assd.codice";
	private static final String FIND_AREA_SSD_BY_PK = "SELECT assd.* FROM aree_ssd assd WHERE assd.id_area_ssd = ?";
	private static final String INSERT_AREA_SSD = "INSERT INTO aree_ssd (codice, denominazione) VALUES (?,?)";
	private static final String UPDATE_AREA_SSD = "UPDATE aree_ssd SET codice=?, denominazione=? WHERE id_area_ssd=?";
	private static final String DELETE_AREA_SSD = "DELETE FROM aree_ssd WHERE id_area_ssd=?";

	private static final String FIND_SSD_BY_PK = "SELECT ssd.* FROM settori_scientifico_disciplinari ssd INNER JOIN aree_ssd areessd ON ssd.id_area_ssd=areessd.id_area_ssd WHERE ssd.id_ssd = ?";
	private static final String INSERT_SSD = "INSERT INTO settori_scientifico_disciplinari (codice, denominazione, id_area_ssd) VALUES (?,?,?)";
	private static final String UPDATE_SSD = "UPDATE settori_scientifico_disciplinari SET codice=?, denominazione=?, id_area_ssd=? WHERE id_ssd=?";
	private static final String DELETE_SSD = "DELETE FROM settori_scientifico_disciplinari WHERE id_ssd=?";

	@Autowired
	private DataSource dataSource;

	/* AREA SSD */
	@Override
	public ResponseGrid<AreaSSD> findAllAreeSSDPaginated(RequestGrid requestGrid) throws BusinessException {
		String sortCol = requestGrid.getSortCol();
		if ("id".equals(requestGrid.getSortCol())) {
			sortCol = "assd.id_area_ssd";
		}
		String orderBy = (!"".equals(sortCol) && !"".equals(requestGrid.getSortDir())) ? " ORDER BY " + sortCol + " " + requestGrid.getSortDir() : "";
		String baseSearch = "SELECT assd.* " + "FROM aree_ssd assd " + ((!"".equals(requestGrid.getSearch().getValue())) ? " WHERE assd.denominazione like '" + ConversionUtility.addPercentSuffix(requestGrid.getSearch().getValue()) + "'" : "");

		String sql = baseSearch + orderBy + " LIMIT " + requestGrid.getStart() + ", " + requestGrid.getLength();
		String countSql = "SELECT count(*) FROM (" + baseSearch + ") as t1";
		long records = 0;

		List<AreaSSD> areessd = new ArrayList<>();
		try (Connection con = dataSource.getConnection(); Statement st = con.createStatement(); ResultSet rsCount = st.executeQuery(countSql);) {
			// COUNT ELEMENTS
			if (rsCount.next()) {
				records = rsCount.getLong(1);
			}

			// EXECUTE SQL LIMITED
			try (ResultSet rs = st.executeQuery(sql);) {
				while (rs.next()) {
					AreaSSD ssd = new AreaSSD();
					ssd.setId(rs.getLong("id_area_ssd"));
					ssd.setCodice(rs.getString("codice"));
					ssd.setDenominazione(rs.getString("denominazione"));
					areessd.add(ssd);
				}
			}

		} catch (SQLException e) {
			log.error("findAllAreeSSDPaginated", e);
			throw new BusinessException("", e);
		}
		return new ResponseGrid<AreaSSD>(requestGrid.getDraw(), records, records, areessd);

	}

	@Override
	public List<AreaSSD> findAllAree() throws BusinessException {
		List<AreaSSD> result = new ArrayList<>();
		try (Connection con = dataSource.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(FIND_AREE_SSD);) {
			while (rs.next()) {
				AreaSSD areaSSD = new AreaSSD();
				areaSSD.setId(rs.getLong("id_area_ssd"));
				areaSSD.setCodice(rs.getString("codice"));
				areaSSD.setDenominazione(rs.getString("denominazione"));
				result.add(areaSSD);
			}
		} catch (SQLException e) {
			log.error("findAllAree", e);
			throw new BusinessException("findAllAree", e);
		}
		return result;
	}

	@Override
	public AreaSSD findAreaSSDByID(Long id) throws BusinessException {
		AreaSSD result = null;
		try (Connection con = dataSource.getConnection(); PreparedStatement st = con.prepareStatement(FIND_AREA_SSD_BY_PK);) {
			st.setLong(1, id);
			try (ResultSet rs = st.executeQuery();) {
				if (rs.next()) {
					result = new AreaSSD();
					result.setId(rs.getLong("id_area_ssd"));
					result.setCodice(rs.getString("codice"));
					result.setDenominazione(rs.getString("denominazione"));
				}
			}
		} catch (SQLException e) {
			log.error("findAreaSSDByID", e);
			throw new BusinessException("findAreaSSDByID", e);
		}
		return result;
	}

	@Override
	public void createAreaSSD(AreaSSD areaSSD) throws BusinessException {
		try (Connection con = dataSource.getConnection(); PreparedStatement st = con.prepareStatement(INSERT_AREA_SSD);) {
			st.setString(1, areaSSD.getCodice());
			st.setString(2, areaSSD.getDenominazione());
			st.executeUpdate();
		} catch (SQLException e) {
			log.error("createAreaSSD", e);
			throw new BusinessException("createAreaSSD", e);
		}
	}

	@Override
	public void updateAreaSSD(AreaSSD areaSSD) throws BusinessException {
		try (Connection con = dataSource.getConnection(); PreparedStatement st = con.prepareStatement(UPDATE_AREA_SSD);) {
			st.setString(1, areaSSD.getCodice());
			st.setString(2, areaSSD.getDenominazione());
			st.setLong(3, areaSSD.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			log.error("updateAreaSSD", e);
			throw new BusinessException("updateAreaSSD", e);
		}
	}

	@Override
	public void deleteAreaSSD(AreaSSD areaSSD) throws BusinessException {
		try (Connection con = dataSource.getConnection(); PreparedStatement st = con.prepareStatement(DELETE_AREA_SSD);) {
			st.setLong(1, areaSSD.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			log.error("deleteAreaSSD", e);
			throw new BusinessException("deleteAreaSSD", e);
		}
	}

	/* Settore Scientifico Disciplinare */
	@Override
	public ResponseGrid<SettoreScientificoDisciplinare> findAllSSDPaginated(RequestGrid requestGrid) throws BusinessException {
		String sortCol = requestGrid.getSortCol();
		if ("id".equals(requestGrid.getSortCol())) {
			sortCol = "ssd.id_ssd";
		}
		if ("areaSSD.codice".equals(requestGrid.getSortCol())) {
			sortCol = "areessd.codice";
		}
		String orderBy = (!"".equals(sortCol) && !"".equals(requestGrid.getSortDir())) ? " ORDER BY " + sortCol + " " + requestGrid.getSortDir() : "";
		String baseSearch = "SELECT ssd.*, areessd.codice as areessd_codice, areessd.denominazione as areessd_denominazione " + "FROM settori_scientifico_disciplinari ssd INNER JOIN aree_ssd areessd ON ssd.id_area_ssd=areessd.id_area_ssd" + ((!"".equals(requestGrid.getSearch().getValue())) ? " WHERE ssd.denominazione like '" + ConversionUtility.addPercentSuffix(requestGrid.getSearch().getValue()) + "'" : "");
		String sql = baseSearch + orderBy + " LIMIT " + requestGrid.getStart() + ", " + requestGrid.getLength();
		String countSql = "SELECT count(*) FROM (" + baseSearch + ") as t1";
		long records = 0;
		List<SettoreScientificoDisciplinare> ssds = new ArrayList<>();
		try (Connection con = dataSource.getConnection(); Statement st = con.createStatement(); ResultSet rsCount = st.executeQuery(countSql);) {
			// COUNT ELEMENTS
			if (rsCount.next()) {
				records = rsCount.getLong(1);
			}

			// EXECUTE SQL LIMITED
			try (ResultSet rs = st.executeQuery(sql);) {
				while (rs.next()) {
					SettoreScientificoDisciplinare ssd = new SettoreScientificoDisciplinare();
					ssd.setId(rs.getLong("id_ssd"));
					ssd.setCodice(rs.getString("codice"));
					ssd.setDenominazione(rs.getString("denominazione"));
					AreaSSD areassd = new AreaSSD();
					areassd.setId(rs.getLong("id_area_ssd"));
					areassd.setCodice(rs.getString("areessd_codice"));
					areassd.setDenominazione(rs.getString("areessd_denominazione"));
					ssd.setAreaSSD(areassd);
					ssds.add(ssd);
				}
			}

		} catch (SQLException e) {
			log.error("findAllSSDPaginated", e);
			throw new BusinessException("findAllSSDPaginated", e);
		}
		return new ResponseGrid<SettoreScientificoDisciplinare>(requestGrid.getDraw(), records, records, ssds);
	}

	@Override
	public SettoreScientificoDisciplinare findSSDByID(Long id) throws BusinessException {
		SettoreScientificoDisciplinare result = null;
		try (Connection con = dataSource.getConnection(); PreparedStatement st = con.prepareStatement(FIND_SSD_BY_PK);) {
			st.setLong(1, id);
			try (ResultSet rs = st.executeQuery();) {
				if (rs.next()) {
					result = new SettoreScientificoDisciplinare();
					result.setId(rs.getLong("id_ssd"));
					result.setCodice(rs.getString("codice"));
					result.setDenominazione(rs.getString("denominazione"));
					AreaSSD areaSSD = new AreaSSD();
					areaSSD.setId(rs.getLong("id_area_ssd"));
					result.setAreaSSD(areaSSD);
				}
			}
		} catch (SQLException e) {
			log.error("findSSDByID", e);
			throw new BusinessException("findSSDByID", e);
		}
		return result;
	}

	@Override
	public void createSSD(SettoreScientificoDisciplinare ssd) throws BusinessException {
		try (Connection con = dataSource.getConnection(); PreparedStatement st = con.prepareStatement(INSERT_SSD);) {
			st.setString(1, ssd.getCodice());
			st.setString(2, ssd.getDenominazione());
			st.setLong(3, ssd.getAreaSSD().getId());
			st.executeUpdate();
		} catch (SQLException e) {
			log.error("createSSD", e);
			throw new BusinessException("createSSD", e);
		}
	}

	@Override
	public void updateSSD(SettoreScientificoDisciplinare ssd) throws BusinessException {
		try (Connection con = dataSource.getConnection(); PreparedStatement st = con.prepareStatement(UPDATE_SSD);) {
			st.setString(1, ssd.getCodice());
			st.setString(2, ssd.getDenominazione());
			st.setLong(3, ssd.getAreaSSD().getId());
			st.setLong(4, ssd.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			log.error("updateSSD", e);
			throw new BusinessException("updateSSD", e);
		}
	}

	@Override
	public void deleteSSD(SettoreScientificoDisciplinare ssd) throws BusinessException {
		try (Connection con = dataSource.getConnection(); PreparedStatement st = con.prepareStatement(DELETE_SSD);) {
			st.setLong(1, ssd.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			log.error("deleteSSD", e);
			throw new BusinessException("deleteSSD", e);
		}

	}
}
