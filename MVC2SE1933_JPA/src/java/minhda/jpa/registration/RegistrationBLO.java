/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minhda.jpa.registration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author msi2k
 */
public class RegistrationBLO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MVC2SE1933_JPAPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Registration checkLogin(String username, String password) {
        Registration result = null;
        EntityManager em = emf.createEntityManager();
        String jpql = "select r "
                + "from Registration r "
                + "where r.username = :username "
                + "and r.password = :password";
        try {
            Query query = em.createQuery(jpql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            result = (Registration) query.getSingleResult();
        } finally {
            em.close();
        }

        return result;
    }

    public List<Registration> searchLastname(String searchValue) {
        List<Registration> result = null;
        EntityManager em = emf.createEntityManager();
        String jpql = "select r "
                + "from Registration r "
                + "where r.lastname like :lastname";
        try {
            Query query = em.createQuery(jpql);
            query.setParameter("lastname", "%" + searchValue + "%");

            result = query.getResultList();
        } finally {
            em.close();
        }
        return result;
    }
    
    public boolean delete(String username) {
    EntityManager em = this.emf.createEntityManager();
    boolean result = false;

    try {
        Registration tmp = em.find(Registration.class, username);
        
        if (tmp != null) {
            em.getTransaction().begin();
            em.remove(tmp);
            em.getTransaction().commit();
            result = true;
        }
    } catch (Exception e) {
        // Tùy chọn: Log lỗi hoặc xử lý rollback nếu commit thất bại
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
    } finally {
        em.close();
    }

    return result;
}
    
    public boolean updatePassRole(String username, String password, boolean role) {
    EntityManager em = this.emf.createEntityManager();
    boolean result = false;

    try {
        // 1. Tìm kiếm đối tượng Registration bằng khóa chính (username)
        Registration tmp = em.find(Registration.class, username);

        if (tmp != null) {
            // 2. Cập nhật các trường (password và isAdmin/role)
            tmp.setPassword(password);
            tmp.setIsAdmin(role); // Giả định 'role' ánh xạ tới 'isAdmin'

            // 3. Bắt đầu Transaction và thực hiện cập nhật
            em.getTransaction().begin();
            em.merge(tmp); // Sử dụng merge để lưu các thay đổi vào DB
            em.getTransaction().commit();
            result = true;
        }
    } finally {
        // Đảm bảo EntityManager luôn được đóng
        em.close();
    }

    return result;
}

    public void close() {
        if (this.emf != null) {
            if (this.emf.isOpen()) {
                this.emf.close();
            }
        }
    }

}
