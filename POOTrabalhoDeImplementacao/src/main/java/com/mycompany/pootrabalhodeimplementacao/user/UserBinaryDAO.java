/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootrabalhodeimplementacao.user;

import com.mycompany.pootrabalhodeimplementacao.data_access_object.binary.BinaryFileDataAccessObject;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;

/**
 *
 * @author felipe
 */
public class UserBinaryDAO extends BinaryFileDataAccessObject<User> {

    protected static final int NEXT_ID_SIZE = Integer.BYTES;
    protected static final long HEADER_SIZE = NEXT_ID_SIZE;

    public UserBinaryDAO(Path filePath) {
        super(filePath, UserBinaryDAO.HEADER_SIZE, User.RECORD_SIZE, (byte[] byteArray) -> {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);

                DataInputStream in = new DataInputStream(bis);
                int uId = in.readInt();

                byte[] nameBytes = new byte[User.NAME_SIZE];
                in.readFully(nameBytes);
                String uName = new String(nameBytes).trim();

                byte[] loginBytes = new byte[User.LOGIN_SIZE];
                in.readFully(loginBytes);
                String uLogin = new String(loginBytes).trim();

                byte[] passwordBytes = new byte[User.PASSWORD_SIZE];
                in.readFully(passwordBytes);
                String uPassword = new String(passwordBytes).trim();

                int uAccessLevel = in.readInt();

                return new User(uId, uName, uLogin, uPassword, uAccessLevel);
            } catch (IOException e) {
                return null;
            }
        });
    }

    @Override
    public final byte[] getDefaultHeader() {
        return this.createHeader(0);
    }

    public final byte[] createHeader(int nextId) {
        byte[] headerByteArray = new byte[(int) UserBinaryDAO.HEADER_SIZE];
        ByteBuffer bb = ByteBuffer.wrap(headerByteArray);
        bb.putInt(nextId);
        return headerByteArray;
    }

    @Override
    public void addRecord(User user) throws IOException {
        super.addRecord(user);

        if (user.getId() >= this.getNextId()) {
            this.setHeader(this.createHeader(user.getId() + 1));
        }
    }

    public int getNextId() {
        byte[] headerByteArray = this.getHeader();
        ByteBuffer bb = ByteBuffer.wrap(headerByteArray);

        int nextId = bb.getInt();
        return nextId;
    }
}
