package com.hosplanet.api;


import android.content.res.Resources;

import com.hosplanet.R;
import com.hosplanet.common.util.CommonUtil;

/**
 * Created by hyunwoo-Lee on 2015-12-16.
 */
public class HospitalInfoApiBean{

    public static  String serviceKey = CommonUtil.getString(R.string.hospApiKey);
    public static  String apiUrl =  CommonUtil.getString(R.string.hospApiUrl) ; //https://www.data.go.kr/ 공공데이터 포탈
    private String type;
    private Integer pageNo = 1;          //페이지번호 in,out
    private Integer numOfRows = 10;     //한페이지 결과수 in ,검색건수 out
    private String sidoCd;          //시도코드 in,out
    private String sidoCdNm;        //시도명 out
    private String sgguCd;          //시군구코드 in,out
    private String sgguCdNm;       //시군구명 out
    private String emdongNm;       //읍면동명 in,out
    private String yadmnm;         //병원명 in,out
    private String zipCd;          //분류코드 in
    private String clCd;           //종별코드 in,out
    private String clCdNm;        //종별코드명 out
    private String dgsbjtCd;      //진료과목코드 in
    private double xPos;         //X좌표 in
    private double yPos;         //Y좌표 in
    private Integer radius;      //반경경 in
    private String ykiho;        //암호화된 요양기호 out
    private String postNo;       //우편번호 out
    private String addr;         //주소 out
    private String telno;        // 전화번호 out
    private String hospUrl;      // 홈페이지 out
    private String estbDd;       //개설일자 out
    private Integer drTotCnt;    //의사총수 out
    private Integer gdrCnt;      //일반의 인원수 out
    private Integer intnCnt;     //인턴 인원수 out
    private Integer resdntCnt;   //레지던트 인원수 out
    private Integer sdrCnt;      //전문의 인원수 out
    private Integer distance;    //거리 out

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(Integer numOfRows) {
        this.numOfRows = numOfRows;
    }

    public String getSidoCd() {
        return sidoCd;
    }

    public void setSidoCd(String sidoCd) {
        this.sidoCd = sidoCd;
    }

    public String getSidoCdNm() {
        return sidoCdNm;
    }

    public void setSidoCdNm(String sidoCdNm) {
        this.sidoCdNm = sidoCdNm;
    }

    public String getSgguCd() {
        return sgguCd;
    }

    public void setSgguCd(String sgguCd) {
        this.sgguCd = sgguCd;
    }

    public String getSgguCdNm() {
        return sgguCdNm;
    }

    public void setSgguCdNm(String sgguCdNm) {
        this.sgguCdNm = sgguCdNm;
    }

    public String getEmdongNm() {
        return emdongNm;
    }

    public void setEmdongNm(String emdongNm) {
        this.emdongNm = emdongNm;
    }

    public String getYadmnm() {
        return yadmnm;
    }

    public void setYadmnm(String yadmnm) {
        this.yadmnm = yadmnm;
    }

    public String getZipCd() {
        return zipCd;
    }

    public void setZipCd(String zipCd) {
        this.zipCd = zipCd;
    }

    public String getClCd() {
        return clCd;
    }

    public void setClCd(String clCd) {
        this.clCd = clCd;
    }

    public String getClCdNm() {
        return clCdNm;
    }

    public void setClCdNm(String clCdNm) {
        this.clCdNm = clCdNm;
    }

    public String getDgsbjtCd() {
        return dgsbjtCd;
    }

    public void setDgsbjtCd(String dgsbjtCd) {
        this.dgsbjtCd = dgsbjtCd;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public String getYkiho() {
        return ykiho;
    }

    public void setYkiho(String ykiho) {
        this.ykiho = ykiho;
    }

    public String getPostNo() {
        return postNo;
    }

    public void setPostNo(String postNo) {
        this.postNo = postNo;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getHospUrl() {
        return hospUrl;
    }

    public void setHospUrl(String hospUrl) {
        this.hospUrl = hospUrl;
    }

    public String getEstbDd() {
        return estbDd;
    }

    public void setEstbDd(String estbDd) {
        this.estbDd = estbDd;
    }

    public Integer getDrTotCnt() {
        return drTotCnt;
    }

    public void setDrTotCnt(Integer drTotCnt) {
        this.drTotCnt = drTotCnt;
    }

    public Integer getGdrCnt() {
        return gdrCnt;
    }

    public void setGdrCnt(Integer gdrCnt) {
        this.gdrCnt = gdrCnt;
    }

    public Integer getIntnCnt() {
        return intnCnt;
    }

    public void setIntnCnt(Integer intnCnt) {
        this.intnCnt = intnCnt;
    }

    public Integer getResdntCnt() {
        return resdntCnt;
    }

    public void setResdntCnt(Integer resdntCnt) {
        this.resdntCnt = resdntCnt;
    }

    public Integer getSdrCnt() {
        return sdrCnt;
    }

    public void setSdrCnt(Integer sdrCnt) {
        this.sdrCnt = sdrCnt;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
