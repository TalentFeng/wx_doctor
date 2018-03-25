package com.lqf.wxdoctor.domain;

public class Case {
  private Long id;
  private Long uid;
  private String blh2;
  private String inspection_item;
  private String inspection_site;
  private String report_status;
  private String report_doctor;
  private String audit_doctor;
  private String result;
  private String fee_remark;
  private String fee_status;
  private java.sql.Date reg_date;
  private java.sql.Date rep_date;
  private java.sql.Date prt_date;
  private String sj_doc_phone;
  private String clinical_diagnosis;
  private java.sql.Date audit_date;
  private String department;
  private String reg_doc;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public String getBlh2() {
    return blh2;
  }

  public void setBlh2(String blh2) {
    this.blh2 = blh2;
  }

  public String getInspection_item() {
    return inspection_item;
  }

  public void setInspection_item(String inspection_item) {
    this.inspection_item = inspection_item;
  }

  public String getInspection_site() {
    return inspection_site;
  }

  public void setInspection_site(String inspection_site) {
    this.inspection_site = inspection_site;
  }

  public String getReport_status() {
    return report_status;
  }

  public void setReport_status(String report_status) {
    this.report_status = report_status;
  }

  public String getReport_doctor() {
    return report_doctor;
  }

  public void setReport_doctor(String report_doctor) {
    this.report_doctor = report_doctor;
  }

  public String getAudit_doctor() {
    return audit_doctor;
  }

  public void setAudit_doctor(String audit_doctor) {
    this.audit_doctor = audit_doctor;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getFee_remark() {
    return fee_remark;
  }

  public void setFee_remark(String fee_remark) {
    this.fee_remark = fee_remark;
  }

  public String getFee_status() {
    return fee_status;
  }

  public void setFee_status(String fee_status) {
    this.fee_status = fee_status;
  }

  public java.sql.Date getReg_date() {
    return reg_date;
  }

  public void setReg_date(java.sql.Date reg_date) {
    this.reg_date = reg_date;
  }

  public java.sql.Date getRep_date() {
    return rep_date;
  }

  public void setRep_date(java.sql.Date rep_date) {
    this.rep_date = rep_date;
  }

  public java.sql.Date getPrt_date() {
    return prt_date;
  }

  public void setPrt_date(java.sql.Date prt_date) {
    this.prt_date = prt_date;
  }

  public String getSj_doc_phone() {
    return sj_doc_phone;
  }

  public void setSj_doc_phone(String sj_doc_phone) {
    this.sj_doc_phone = sj_doc_phone;
  }

  public String getClinical_diagnosis() {
    return clinical_diagnosis;
  }

  public void setClinical_diagnosis(String clinical_diagnosis) {
    this.clinical_diagnosis = clinical_diagnosis;
  }

  public java.sql.Date getAudit_date() {
    return audit_date;
  }

  public void setAudit_date(java.sql.Date audit_date) {
    this.audit_date = audit_date;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getReg_doc() {
    return reg_doc;
  }

  public void setReg_doc(String reg_doc) {
    this.reg_doc = reg_doc;
  }
}
