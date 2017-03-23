var express = require("express")
var app = express();
var bodyParser = require("body-parser");
var mongoose = require("mongoose");
var Campground = require("./models/campgrounds");
var seedDB = require("./seeds");

mongoose.connect("mongodb://localhost/yelp_camp");
app.use(bodyParser.urlencoded({extended: true}));
app.set("view engine", "ejs");
seedDB();

app.get("/", function(req, res){
    res.render("landing");
});

//INDEX - show all campgrounds
app.get("/campgrounds", function(req, res){
    Campground.find({}, function(err, campgrounds){
        err ? console.log(err) : res.render("campgrounds/index",{campgrounds:campgrounds});
    })
});

//CREATE - add new campground to database
app.post("/campgrounds", function(req, res){
    var name = req.body.name;
    var image = req.body.image;
    var desc = req.body.description;
    var newCampground = {name: name, image: image, description: desc}
    Campground.create(newCampground, function(err, newlyCreated){
        err ? console.log(err) : console.log(newlyCreated);
    });
    res.redirect("/campgrounds");
});

//NEW - show form to create campgrounds
app.get("/campgrounds/new", function(rep, res){
   res.render("campgrounds/new"); 
});

//SHOW - shows more info about one campground
app.get("/campgrounds/:id", function(req, res){
    //find the campground with the provided ID
    Campground.findById(req.params.id).populate("comments").exec(function(err, foundCampground){
        //render the show template
        err ? (console.log(err)
        ) : (
            console.log(foundCampground),
            res.render("campgrounds/show", {campground: foundCampground})); 
    })
});

//New - Post new comments for campground
app.get("/campgrounds/:id/comments/new", function(req, res){
    //find campground by id
    Campground.findById(req.params.id, function(err, campground){
        err ? (console.log(err)
        ) : ( 
            res.render("comments/new", {campground: campground}));
    })
    
})

app.listen(process.env.PORT, process.env.IP, function(){
    console.log("The YelpCamp server has started");
})