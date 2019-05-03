package com.chasegarsee.projectgdp.controller;

import com.chasegarsee.projectgdp.ProjectgdpApplication;
import com.chasegarsee.projectgdp.exception.ResourceNotFoundException;
import com.chasegarsee.projectgdp.model.GDP;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.Comparator;

@RestController
public class GDPController
{

    private static final Logger logger = LoggerFactory.getLogger(GDP.class);
//      /names - return using the JSON format all of the countries alphabetized by name
        //localhost:1992/names/
    @GetMapping(value = "/names", produces = {"application/json"})
    public ResponseEntity<?> allCountries ()
    {
        logger.info("GOT ALL COUNTRIES");
        return new ResponseEntity<>(ProjectgdpApplication.countryList.gdpList, HttpStatus.OK);
    }

//      /economy - return using the JSON format all of the countries sorted from most to least GDP
        //localhost:1992/economy
    @GetMapping(value = "/economy")
    public ResponseEntity<?> countriesByGDPDecending()
    {
        ArrayList<GDP> gdpList = ProjectgdpApplication.countryList.gdpList;
        gdpList.sort(Comparator.comparingLong(GDP::getGdp));

        logger.info("COUNTRIES SORTED");
        return new ResponseEntity<>(gdpList, HttpStatus.OK);
    }

//      /gdp/{country name} - return using the JSON format the record for the named country. Must be spelled as in the data however the search should NOT be case sensitive.
        //localhost:1992/gdp/{country name}

    @GetMapping(value="/gdp/{name}")
    public ResponseEntity<?> getCountryByName(@PathVariable String name) throws ResourceNotFoundException
    {

        GDP rtnGdp = ProjectgdpApplication.countryList.findGDP(g -> g.getName().toLowerCase().equals(name.toLowerCase()));

        if (rtnGdp == null) {
            throw new ResourceNotFoundException("Couldn't find a Country named: " + name);
        }
        logger.info(name + " ACCESSED");
        return new ResponseEntity<>(rtnGdp, HttpStatus.OK);
    }

//      /country/{id} - return using the JSON format a single country and GDP based off of its id number
        //localhost:1992/country/{id}
    @GetMapping(value = "country/{id}", produces = {"application/json"})
    public ResponseEntity<?> getId(@PathVariable long id) throws ResourceNotFoundException
    {
        GDP rtnGDP = ProjectgdpApplication.countryList.findGDP(g -> (g.getId() == id));
        {
            if (rtnGDP == null)
            {
                throw new ResourceNotFoundException("Country with this id " + id + " NOT FOUND");
            }
            logger.info("COUNTRY #" + id + " ACCESSED");
            return new ResponseEntity<>(rtnGDP, HttpStatus.OK);
        }
    }


//      /country/stats/median - return using the JSON the country and its GDP with the median GDP. For odd number list,
        //return the the country in the middle. For even number list you may return either one of the countries found in the middle.
        //localhost:1992/country/stats/median
    @GetMapping(value = "/country/stats/median")
    public ResponseEntity<?> getMedianGdp()
    {
        ArrayList<GDP> gdpList = ProjectgdpApplication.countryList.gdpList;
        gdpList.sort(Comparator.comparingLong(GDP::getGdp));
        GDP rtnGdp = gdpList.get(gdpList.size() / 2);

        return new ResponseEntity<>(rtnGdp, HttpStatus.OK);
    }

    @GetMapping(value="/economy/table")
    public ModelAndView getCountriesTableGreaterThanGDP()
    {

        ArrayList<GDP> gdpList = ProjectgdpApplication.countryList.gdpList;
        gdpList.sort(Comparator.comparingLong(GDP::getGdp));

        ModelAndView mav = new ModelAndView();
        mav.setViewName("countries");
        mav.addObject("gdpList", gdpList);

        return mav;
    }

}

