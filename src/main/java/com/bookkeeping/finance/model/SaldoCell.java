package com.bookkeeping.finance.model;

import com.bookkeeping.finance.model.bace.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "SaldoCell")
@Entity
public class SaldoCell extends BaseEntity {
	int amount;
	String name = "";
	Boolean isConst = false;
}
