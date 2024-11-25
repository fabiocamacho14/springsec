package com.eazybytes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notice_details")
public class NoticeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Integer noticeId;

    @Column(name = "notice_summary", length = 200, nullable = false)
    private String noticeSummary;

    @Column(name = "notice_details", length = 500, nullable = false)
    private String noticeDetails;

    @Column(name = "notic_beg_dt", nullable = false)
    private LocalDateTime noticBegDt;

    @Column(name = "notic_end_dt", nullable = false)
    private LocalDateTime noticEndDt;

    @JsonIgnore
    @Column(name = "create_dt")
    @CreationTimestamp
    private LocalDateTime createDt;

    @JsonIgnore
    @Column(name = "update_dt")
    @UpdateTimestamp
    private LocalDateTime updateDt;
}
