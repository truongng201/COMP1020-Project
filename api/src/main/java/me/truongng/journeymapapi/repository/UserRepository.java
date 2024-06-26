package me.truongng.journeymapapi.repository;

import java.util.List;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import me.truongng.journeymapapi.models.User;
import me.truongng.journeymapapi.models.Config;

@Repository
public class UserRepository implements UserRepositoryInterface {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean create(User user) {
        int res = jdbcTemplate.update(
                "INSERT INTO users (username, hashed_password, email, role, image_url, is_verified) VALUES (?, ?, ?, ?, ?, ?)",
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getRole(),
                user.getImage_url(),
                user.getIs_verified());

        return res == 1 ? true : false;
    }

    @Override
    public boolean update(User user) {
        int res = jdbcTemplate.update(
                "UPDATE users SET username = ?, password = ?, email = ?, role = ?, image_url = ?, is_verified = ? WHERE id = ?",
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getRole(),
                user.getImage_url(),
                user.getIs_verified(),
                user.getId());

        return res == 1 ? true : false;
    }

    @Override
    public List<User> findById(String id) {
        return jdbcTemplate.query(
                "SELECT id, username, email, hashed_password, image_url, is_verified, role FROM users WHERE id = ?",
                (ResultSet rs, int rowNum) -> new User(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("hashed_password"),
                        rs.getString("image_url"),
                        rs.getBoolean("is_verified"),
                        Config.Role.valueOf(rs.getString("role"))),
                Integer.parseInt(id));
    }

    @Override
    public boolean deleteById(String id) {
        return jdbcTemplate.update(
                "DELETE FROM users WHERE id = ?",
                id) == 1;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT id, username, email, role, image_url, is_verified FROM users",
                (ResultSet rs, int rowNum) -> new User(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("hashed_password"),
                        rs.getString("image_url"),
                        rs.getBoolean("is_verified"),
                        Config.Role.valueOf(rs.getString("role"))));
    }

    @Override
    public List<User> findByEmail(String email) {
        return jdbcTemplate.query(
                "SELECT id, username, hashed_password, email, role, image_url, is_verified FROM users WHERE email = ?",
                (ResultSet rs, int rowNum) -> new User(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("hashed_password"),
                        rs.getString("image_url"),
                        rs.getBoolean("is_verified"),
                        Config.Role.valueOf(rs.getString("role"))),
                email);

    }
}