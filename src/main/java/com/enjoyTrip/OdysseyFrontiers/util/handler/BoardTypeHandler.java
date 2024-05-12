package com.enjoyTrip.OdysseyFrontiers.util.handler;

import com.enjoyTrip.OdysseyFrontiers.util.BoardType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(BoardType.class)
public class BoardTypeHandler implements TypeHandler<BoardType> {
    @Override
    public void setParameter(PreparedStatement ps, int i, BoardType parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public BoardType getResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public BoardType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public BoardType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }

    private BoardType getCodeEnum(String code) {
        return switch (code) {
            case "community" -> BoardType.COMMUNITY;
            case "notice" -> BoardType.NOTICE;
            default -> null;
        };
    }
}