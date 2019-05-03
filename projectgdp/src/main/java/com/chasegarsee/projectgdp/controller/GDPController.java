package com.chasegarsee.projectgdp.controller;

import com.chasegarsee.projectgdp.ProjectgdpApplication;
import com.chasegarsee.projectgdp.exception.ResourceNotFoundException;
import com.chasegarsee.projectgdp.model.GDP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GDPController
{
//      /names - return using the JSON format all of the countries alphabetized by name
        //localhost:1992/names/
    @GetMapping(value = "/names", produces = {"application/json"})
    public ResponseEntity<?> allCountries ()
    {
        return new ResponseEntity<>(ProjectgdpApplication.countryList.gdpList, HttpStatus.OK);
    }

//      /economy - return using the JSON format all of the countries sorted from most to least GDP
        //localhost:1992/economy

//      /gdp/{country name} - return using the JSON format the record for the named country. Must be spelled as in the data however the search should NOT be case sensitive.
        //localhost:1992/gdp/{country name}

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
            return new ResponseEntity<>(rtnGDP, HttpStatus.OK);
        }
    }


//      /country/stats/median - return using the JSON the country and its GDP with the median GDP. For odd number list,
        //return the the country in the middle. For even number list you may return either one of the countries found in the middle.
        //localhost:1992/country/stats/median


}

