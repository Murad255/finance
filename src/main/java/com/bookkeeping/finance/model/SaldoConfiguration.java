package com.bookkeeping.finance.model;

import com.bookkeeping.finance.model.bace.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "SaldoConfiguration")
@Entity
public class SaldoConfiguration  extends BaseEntity {
	int investmentsAmount;
	String investmentsName;
	int startedDateMonth;
	int startedDateYear;
	String currentCurrency = "₽";
}
