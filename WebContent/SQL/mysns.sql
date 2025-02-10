CREATE DATABASE IF NOT EXISTS mysns
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE mysns;

-- 사용자 테이블 생성 (password, name, ts 주석 처리)
CREATE TABLE IF NOT EXISTS user ( 
    id VARCHAR(128) PRIMARY KEY,    -- 이메일(아이디)
    -- password VARCHAR(32),        -- 패스워드 컬럼 주석 처리
    -- name VARCHAR(32),            -- 사용자 이름 주석 처리
    -- ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 가입 시간 주석 처리
    jsonstr VARCHAR(1024)           -- JSON 데이터 컬럼
);

-- 피드 테이블 생성
CREATE TABLE IF NOT EXISTS feed (
    no INT UNSIGNED PRIMARY KEY,  -- 피드 번호
    id VARCHAR(128),                              -- 사용자 ID
    -- content VARCHAR(4096),                        -- 콘텐츠 내용
    -- images VARCHAR(1024),                         -- 이미지 경로
    -- ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,      -- 게시 시간
	jsonstr VARCHAR(8192)
);
CREATE TABLE IF NOT EXISTS friend (
	id VARCHAR(128),
	frid VARCHAR(128),
	INDEX idx1(id)
);

    
    
    
    
    
    
    