var mongoose = require("mongoose");
var Campground = require("./models/campgrounds");
var Comment = require("./models/comment");

var data = [
    {
        name: "Cloud's Rest",
        image: "http://www.photosforclass.com/download/7842069486",
        description: "so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil "
    },   
    {
        name: "Dry Sands",
        image: "http://www.photosforclass.com/download/2164766085",
        description: "so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil "
    },
    {
        name: "McDouglas Forest",
        image: "http://www.photosforclass.com/download/7930028902",
        description: "so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil "
    }
]

function seedDB(){
    //Remove all campgrounds
    Campground.remove({}, function(err){
        err ? console.log(err) : console.log("removed campgrounds");
        //add a few campgrounds
        data.forEach(function(seed){
            Campground.create(seed, function(err, campground){
                err ? console.log(err) : console.log("Added a campground");
                //crate a comment
                Comment.create(
                    {
                        text: "This place is great but I wish it had internet",
                        author: "Homer"
                    }, function(err, comment){
                        err ? (
                            console.log(err) 
                        ) : (campground.comments.push(comment),
                            campground.save()),
                            console.log("Created new comment")
                    })
            })
        });
    });

    //add a few comments
}

module.exports = seedDB;