package prog.pbl.Testes.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog.pbl.LibraryException.usersexcepitions.AdministradorException;
import prog.pbl.dao.MasterDao;
import prog.pbl.dao.usuarios.ImDiskAdministradorDao;
import prog.pbl.model.usuarios.Administrador;

import static org.junit.jupiter.api.Assertions.*;

class ImDiskAdministradorDaoTest {

    private Administrador adm;
    private ImDiskAdministradorDao dao;




    @BeforeEach
    void setUp() throws Exception {
        adm = new Administrador("123","Maike","Ditador","62909475085");
        //MasterDao.getAdministradorDao().save(adm);
        dao = new ImDiskAdministradorDao();
        dao.save(adm);
    }
    @AfterEach
    void setDown() throws Exception{
        MasterDao.getAdministradorDao().clearAll();
    }

    @Test
    void findById() throws AdministradorException {
        assertEquals(adm,MasterDao.getAdministradorDao().findById(adm.getId()));
    }

    @Test
    void save() throws AdministradorException {
        assertEquals(adm.getId(),MasterDao.getAdministradorDao().findById(adm.getId()).getId());
    }

    @Test
    void delete() throws AdministradorException{
        MasterDao.getAdministradorDao().delete(adm);
        assertEquals(0, MasterDao.getAdministradorDao().findAll().size());
    }

    @Test
    void update() throws AdministradorException {
        Administrador novoAdm = new Administrador("222","MaikeTest","Adm","83383727068");
        MasterDao.getAdministradorDao().Update(novoAdm,adm);
        assertNotEquals(adm,MasterDao.getAdministradorDao().findById(novoAdm.getId()));
    }

    @Test
    void findAll() throws AdministradorException{
        assertEquals(1, MasterDao.getAdministradorDao().findAll().size());
    }

    @Test
    void findLogin() throws AdministradorException{
        Administrador novoAdm = new Administrador("222","MaikeTest","Adm","83383727068");
        MasterDao.getAdministradorDao().save(novoAdm);
        Administrador sessao = MasterDao.getAdministradorDao().findLogin(adm.getId(),adm.getSenha());
        assertEquals(adm, sessao);
    }
}