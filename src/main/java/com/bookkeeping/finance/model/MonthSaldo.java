package com.bookkeeping.finance.model;

import com.bookkeeping.finance.model.bace.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "MonthSaldo")
@Entity
public class MonthSaldo extends BaseEntity {
	@OneToMany(
			cascade = CascadeType.MERGE,
			fetch = FetchType.EAGER
	)
	@JoinTable(name = "MonthSaldo_incomes_SaldoCell",
			joinColumns = {
					@JoinColumn(name = "MonthSaldo_incomes_id", referencedColumnName = "id",
							nullable = false, updatable = false)},
			inverseJoinColumns = {
					@JoinColumn(name = "SaldoCell_id", referencedColumnName = "id",
							nullable = false, updatable = false)})
	List<SaldoCell> incomes = new ArrayList<SaldoCell>();

	@OneToMany(
			cascade = CascadeType.ALL
			//fetch = FetchType.EAGER
	)
	@JoinTable(name = "MonthSaldo_expenses_SaldoCell",
			joinColumns = {
					@JoinColumn(name = "MonthSaldo_expenses_id", referencedColumnName = "id",
							nullable = false, updatable = false)},
			inverseJoinColumns = {
					@JoinColumn(name = "SaldoCell_id", referencedColumnName = "id",
							nullable = false, updatable = false)})
	List<SaldoCell> expenses = new ArrayList<SaldoCell>();
}
