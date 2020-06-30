package edu.pja.mas.eventmasfinal;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import edu.pja.mas.eventmasfinal.entity.*;
import edu.pja.mas.eventmasfinal.repository.*;
import java.util.EnumSet;

@Component
public class DataInitializer {
	@Autowired
	private OrganizerRepo orgRepo;
	@Autowired
	private VolunteerRepo volRepo;
	@Autowired
	private SponsorRepo spnRepo;
	@Autowired
	private ISupportRepo supportRepo;
	@Autowired
	private ActivityRepo activityRepo;
	@Autowired
	private EventRepo eventRepo;
	@Autowired
	private OrganizesWhenRepo organizesWhenRepo;
	@Autowired
	private OrganizersGroupRepo ogRepo;

	public void initData() {
		LocalDate d1 = LocalDate.of(2019, 10, 10);
		LocalDate d2 = LocalDate.of(2020, 12, 10);
		LocalDate d3 = LocalDate.of(2020, 8, 12);

		Organizer org2 = new Organizer("Ashley", "Down", 222222, "222-222-222", 1230);
		Organizer org3 = new Organizer("Billie", "Endson", 333333, "725-272-222", 4130);
		Organizer org4 = new Organizer("Sam", "Smith", 444444, "222-555-222", 8530);


		Organizer[] orgs = { org2, org3, org4 };
		List orgList = Arrays.asList(orgs);
		orgRepo.saveAll(orgList);
		
		Event ev1 = new Event("StarWars", "KinoTeka", 1500, 15, EnumSet.of(Event.EventType.Premiere), 150.0);
		Event ev2 = new Event("PartyLOL", "Centrum", 500, 20, EnumSet.of(Event.EventType.Party), "Murda", "RedHat");
		Event ev3 = new Event("highSchool-Reunion", "BUW", 2500, 200,
				EnumSet.of(Event.EventType.Reunion, Event.EventType.Party), "yearly", "Murda", "RedHat");
		Event ev4 = new Event("LOL", "KabatyForest", 1000, 10, EnumSet.of(Event.EventType.Party), "Ezhel", "StayGreen");
		Event ev5 = new Event("Titanic", "Maximus", 1500, 15, EnumSet.of(Event.EventType.Premiere), 150.0);

		eventRepo.save(ev1);
		eventRepo.save(ev2);
		eventRepo.save(ev3);
		eventRepo.save(ev4);
		eventRepo.save(ev5);
		orgRepo.save(org2);

		// Bag
		OrganizesWhen ow1 = new OrganizesWhen(d2, ev2, org2);
		organizesWhenRepo.save(ow1);
		eventRepo.save(ev2);
		
		OrganizesWhen ow2 = new OrganizesWhen(d1, ev3, org2);
		organizesWhenRepo.save(ow2);
		eventRepo.save(ev3);
		
		OrganizesWhen ow3 = new OrganizesWhen(d3, ev4, org3);
		organizesWhenRepo.save(ow3);
		eventRepo.save(ev4);

		Volunteer vol2 = new Volunteer("Ashley", "Down", 666666, "222-222-526", 1453);
		volRepo.save(vol2);



		// event - financial support (one to many)
		FinancialSupport fs1 = new FinancialSupport(12000);
		Support financial1 = new Support(fs1, null);

		FinancialSupport fs2 = new FinancialSupport(7000);
		Support financial2 = new Support(fs2, null);
		FinancialSupport fs3 = new FinancialSupport(5000);
		Support financial3 = new Support(fs3, null);
		// event - financial support (one to many)
		FinancialSupport fs4 = new FinancialSupport(1200);
		Support financial4 = new Support(fs4, null);
		FinancialSupport fs5 = new FinancialSupport(4000);
		Support financial5 = new Support(fs5, null);
		FinancialSupport fs6 = new FinancialSupport(9000);
		Support financial6 = new Support(fs6, null);
		FinancialSupport fs7 = new FinancialSupport(4000);
		Support financial7 = new Support(fs7, null);
		FinancialSupport fs8 = new FinancialSupport(9000);
		Support financial8 = new Support(fs8, null);

		fs1.setEvent(ev3);
		fs2.setEvent(ev3);
		fs3.setEvent(ev2);
		fs4.setEvent(ev4);
		fs5.setEvent(ev4);
		fs6.setEvent(ev5);
		fs7.setEvent(ev1);
		fs8.setEvent(ev1);


		Support[] supports = { financial1, financial2, financial3,financial4, financial5, financial6, financial7, financial8 };
		List supportsList = Arrays.asList(supports);
		supportRepo.saveAll(supportsList);


		Event[] events = { ev1, ev2, ev3, ev4, ev5 };
		List eventList = Arrays.asList(events);
		eventRepo.saveAll(eventList);



		// event - goods support (one to many)
		GoodsSupport gs2 = new GoodsSupport(50, "Desk");
		Support good2 = new Support(null, gs2);
		GoodsSupport gs1 = new GoodsSupport(200, "Chair");
		Support good1 = new Support(null, gs1);
		GoodsSupport gs3 = new GoodsSupport(100, "Plate");
		Support good3 = new Support(null, gs3);
		GoodsSupport gs4 = new GoodsSupport(20, "Costume");
		Support good4 = new Support(null, gs4);
		GoodsSupport gs5 = new GoodsSupport(120, "Wine");
		Support good5 = new Support(null, gs5);
		GoodsSupport gs6 = new GoodsSupport(500, "Drinks");
		Support good6 = new Support(null, gs6);
		GoodsSupport gs7 = new GoodsSupport(700, "Food");
		Support good7 = new Support(null, gs7);


		gs1.setEvent(ev2);
		supportRepo.save(good1);
		gs2.setEvent(ev1);
		supportRepo.save(good2);
		gs3.setEvent(ev3);
		supportRepo.save(good3);
		gs4.setEvent(ev4);
		supportRepo.save(good4);
		gs5.setEvent(ev1);
		supportRepo.save(good5);
		gs6.setEvent(ev5);
		supportRepo.save(good6);
		gs7.setEvent(ev4);
		supportRepo.save(good7);
		Event[] events1 = { ev1, ev2, ev3, ev4, ev5 };
		List eventList1 = Arrays.asList(events1);
		eventRepo.saveAll(eventList1);




		// ** error - nullpointerException - qualified association

		Sponsor spn2 = new Sponsor("Bob", "Marley", 555555, "777-322-222", "456456");
		// spn2.setSupport(good1);
		spnRepo.save(spn2);

		// composition 
		IndoorActivity.createIndoorActivity(ev1, "Puzzle", 100);
		IndoorActivity.createIndoorActivity(ev2, "Board Game", 200);
		OutdoorActivity.createOutdoorActivity(ev3, "Picnic", "Food");
		
		eventRepo.save(ev1);
		eventRepo.save(ev2);
		eventRepo.save(ev3);

		//	OrganizersGroup og1 = new OrganizersGroup("Lions", 12, null, true, OrganizersGroup.Status.ACTIVE);
		//	OrganizersGroup og2 = new OrganizersGroup("kingdom", 10, null, true, OrganizersGroup.Status.ACTIVE);
		//	ogRepo.save(og1);
		//	ogRepo.save(og2);


		//	og1.addOrganizer(org2);
		//	og1.addOrganizer(org3);
		//	og1.setManager(org2);

	}
}
