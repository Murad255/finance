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
@Table(name = "SaveContainer")
@Entity
public class SaveContainer extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "config_id")
	SaldoConfiguration config;

	@OneToMany(
			cascade = CascadeType.ALL
			//fetch = FetchType.EAGER
	)	//@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(name = "SaveContainer_MonthSaldo",
			joinColumns = {
					@JoinColumn(name = "SaveContainer_id", referencedColumnName = "id",
							nullable = false, updatable = false)},
			inverseJoinColumns = {
					@JoinColumn(name = "MonthSaldo_id", referencedColumnName = "id",
							nullable = false, updatable = false)})
	List<MonthSaldo> data = new ArrayList<MonthSaldo>();
}
