package com.bookkeeping.finance.model.bace;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
// Choose your inheritance strategy:
//@Inheritance(strategy=InheritanceType.JOINED)
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {
	@Id
	@GeneratedValue //(strategy = GenerationType.AUTO)
	private Long id;
}