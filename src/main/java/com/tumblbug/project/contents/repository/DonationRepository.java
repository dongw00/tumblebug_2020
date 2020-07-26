package com.tumblbug.project.contents.repository;

import com.tumblbug.project.contents.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
