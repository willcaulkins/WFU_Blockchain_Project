// SPDX-License-Identifier: GPL-3.0

pragma solidity 0.8.4;

contract Main {
    uint256 public peopleCount = 0;
    uint256 public artefactCount = 0;
    uint256 public transacCount = 0;
    uint256 public itemCount = 0;
    
    mapping(uint => Stakeholder) public people;
    mapping(uint => Artefact) public artefacts;
    mapping(uint => Transaction) public transacs;
    
    struct Stakeholder {
        // address wallet;
        uint256 id;
        string name;
        string location;
    }
    
    struct Artefact {
        // address wallet;
        uint id;
        string name;
        // Stakeholder titleOwner;
        // Stakeholder currentPossessor;
        int256 revShareToTitleOwner;
        // string creator; // "Leonardo DaVinci: birthDate-deathDate"
        // Long dateOfCreation;
        string countryOfOrigin;
        // string region
        string medium;
        int256 dimensionHeight;
        int256 dimensionWidth;
        int256 dimensionDepth;
        int256 weightKg;
        int256 appraisedValue;
    }
    
    struct Transaction {
        uint id;
        // Artefact artefact;
        // Long timestamp;
        Stakeholder seller;
        Stakeholder buyer;
        string facilitator;
        int256 price;
    }
    
    constructor() {
        peopleCount += 1;
        itemCount += 1;
        people[peopleCount] = Stakeholder(itemCount, "Guy One", "Winston-Salem");
        artefactCount += 1;
        itemCount +=1;
        artefacts[artefactCount] = Artefact(itemCount, "Artefact One", 25, "USA", "Stone", 2, 4, 6, 8, 100);
    }
    
    function addStakeholder(string memory _name, string memory _location) public {
        peopleCount += 1;
        itemCount += 1;
        people[peopleCount] = Stakeholder(itemCount, _name, _location);
    }
    
    function addArtefact(string memory _name, int256 _revShareToTitleOwner, string memory _countryOfOrigin, string memory _medium, int256 _dimensionHeight, int256 _dimensionWidth, int256 _dimensionDepth, int256 _weightKg, int256 _appraisedValue) public {
        artefactCount += 1;
        itemCount +=1;
        Artefact memory currArt = Artefact(0,"unknown",40, "unknown", "unknown", 0, 0, 0, 0, 0 );
        currArt.id = itemCount;
        currArt.name = _name;
        currArt.revShareToTitleOwner = _revShareToTitleOwner;
        currArt.countryOfOrigin = _countryOfOrigin;
        currArt.medium = _medium;
        currArt.dimensionHeight = _dimensionHeight;
        currArt.dimensionWidth = _dimensionWidth;
        currArt.dimensionDepth = _dimensionDepth;
        currArt.weightKg = _weightKg;
        currArt.appraisedValue = _appraisedValue;
        artefacts[artefactCount] = currArt;
    }
    
    function newTransaction(string memory _facilitator, int256 _price) public {
        transacCount +=1;
        itemCount += 1;
        transacs[transacCount] = Transaction(itemCount, people[1], people[1], _facilitator, _price);
    }
    
}








