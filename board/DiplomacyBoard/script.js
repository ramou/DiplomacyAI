var draw;


var provinces = [
null,
{x:296, y:441}, // Adriatic_Sea
{x:403, y:524}, // Aegean_Sea
{x:339, y:469}, // Albania
{x:500, y:460}, // Ankara	
{x:302, y:472}, // Apulia
{x:576, y:456}, // Armenia
{x:323, y:250}, // Baltic_Sea
{x:445, y:41}, // Barents_Sea
{x:197, y:317}, // Belgium	
{x:279, y:283}, // Berlin	
{x:484, y:420}, // Black_Sea
{x:289, y:336}, // Bohemia
{x:348, y:199}, // Gulf_of_Bothnia
{x:125, y:334}, // Brest	
{x:353, y:378}, // Budapest	
{x:395, y:443}, // Bulgaria	
{x:410, y:440}, // Bulgaria__ec
{x:399, y:462}, // Bulgaria__sc
{x:191, y:360}, // Burgundy
{x:139, y:188}, // Clyde
{x:439, y:473}, // Constantinople	
{x:256, y:245}, // Denmark	
{x:474, y:546}, // Eastern_Mediterranean
{x:157, y:210}, // Edinburgh	
{x:119, y:307}, // English_Channel
{x:385, y:143}, // Finland
{x:377, y:343}, // Galicia
{x:137, y:388}, // Gascony
{x:366, y:515}, // Greece	
{x:226, y:252}, // Helgoland_Bight
{x:205, y:297}, // Holland	
{x:324, y:540}, // Ionian_Sea
{x:90, y:276}, // Irish_Sea
{x:243, y:295}, // Kiel	
{x:162, y:281}, // London	
{x:382, y:245}, // Livonia
{x:142, y:241}, // Liverpool	
{x:180, y:444}, // Gulf_of_Lyon
{x:23, y:355}, // Mid-Atlantic_Ocean
{x:184, y:402}, // Marseilles	
{x:505, y:226}, // Moscow	
{x:243, y:347}, // Munich	
{x:100, y:536}, // North_Africa
{x:65, y:140}, // North_Atlantic_Ocean
{x:299, y:505}, // Naples	
{x:204, y:215}, // North_Sea
{x:220, y:90}, // Norwegian_Sea
{x:264, y:160}, // Norway	
{x:162, y:346}, // Paris	
{x:168, y:319}, // Picardy
{x:220, y:399}, // Piedmont
{x:34, y:417}, // Portugal	
{x:315, y:283}, // Prussia
{x:264, y:452}, // Rome	
{x:223, y:320}, // Ruhr
{x:415, y:405}, // Rumania	
{x:351, y:438}, // Serbia	
{x:515, y:330}, // Sevastopol	
{x:304, y:314}, // Silesia
{x:260, y:212}, // Skagerrak
{x:490, y:505}, // Smyrna	
{x:64, y:439}, // Spain	
{x:80, y:404}, // Spain__nc
{x:52, y:475}, // Spain__sc
{x:500, y:140}, // St_Petersburg	
{x:472, y:122}, // St_Petersburg__nc
{x:418, y:205}, // St_Petersburg__sc
{x:315, y:140}, // Sweden	
{x:219, y:376}, // Switzerland
{x:570, y:520}, // Syria
{x:305, y:412}, // Trieste	
{x:212, y:542}, // Tunis	
{x:247, y:430}, // Tuscany
{x:277, y:378}, // Tyrolia
{x:246, y:483}, // Tyrrhenian_Sea
{x:427, y:327}, // Ukraine
{x:250, y:408}, // Venice	
{x:314, y:360}, // Vienna	
{x:125, y:285}, // Wales
{x:361, y:315}, // Warsaw	
{x:140, y:492}, // Western_Mediterranean
{x:161, y:254} // Yorkshire
]

var countries = {
	"French":"Blue",
	"Russian":"Mauve",
	"Italian":"Green",
	"German":"Black",
	"Austrian":"Brown",
	"Turkish":"Yellow",
	"English":"Pink",
	"Other":"White"
	};

var moves = [];
var armies = [];
var fleets = [];
var bases = [];

$(document).ready(function() {


  draw = SVG('svgimage');
  var base = draw.circle(8,8);
  base.fill("red");
  base.opacity(0.5);
  base.cx(197).cy(317);
  
  console.log("hello");

  armies.push(drawArmy(9, countries.German)); //Belgium
  
  armies.push(drawArmy(49, countries.French)); //Paris
  
  fleets.push(drawFleet(39, countries.English)); //Mid-Atlantic Ocean

  bases.push(drawBase(49, countries.French)); //Paris

  moves.push(drawArc(49, 14)); //Paris -> Brest


});

function redraw() {

	armies.forEach(clear);
	armis = [];
	fleets.forEach(clear);
        fleets = [];
	bases.forEach(clear);
        bases = [];
}

function clear(item, index) {
	item.opacity(0);
}


/*
<g id="A">
	<path d="M9,-6 L2,0 M9,6 L0,0"/>
	<path d="M-11,-6 v4 h17 a2,2 0,0 0 0,-4z"/>
	<circle r="6"/>
</g>
*/
function  drawArmy(pi, c) {
	var p = provinces[pi];
	var group = draw.group();
	group.path("M9,-6 L2,0 M9,6 L0,0");
	group.path("M-11,-6 v4 h17 a2,2 0,0 0 0,-4z").stroke("black");
	var circ = group.circle(8).stroke("black");
	circ.move(-4,-4);
	group.fill(c);
	group.move(p.x, p.y);
	group.opacity(0.5);
	return group;
}

/*
<g id="F">
	<polygon points="-2,-3 10,-3 -2,-13"/>
	<polygon points="-12,-1 -6,5 6,5 12,-1"/>
</g>
*/
function  drawFleet(pi, c) {
	var p = provinces[pi];
	var group = draw.group();
	group.polygon("-2,-3 10,-3 -2,-13").stroke("black");
	group.polygon("-12,-1 -6,5 6,5 12,-1").stroke("black");
	group.fill(c);
	group.move(p.x, p.y);
	group.opacity(0.5);
	return group;
}
/*
<g id="sc"> <!-- colored SC -->
	<circle r="4"/>
</g>
*/

function  drawBase(pi, c) {
	var p = provinces[pi];
	var group = draw.group();
	var circ = group.circle(6).stroke("black");
	circ.move(3,0);
	group.fill(c);
	group.move(p.x, p.y);
	group.opacity(1);
	return group;
}

function  drawArc(si, di) {
	var s = provinces[si];
	var d = provinces[di];
	var mypath = "M" + s.x + "," + s.y + " Q" + s.x + "," + d.y + " " + d.x + "," + d.y;
	console.log(mypath);
	var arc = draw.path(mypath).fill("none").stroke("black");
	
	
	arc.marker(
		"end", 6, 6, function(add) {
			add.path("M 0 0 L 6 3 L 0 6 z");
		});
	arc.marker(
		"mid", 6, 6, function(add) {
			add.path("M 0 0 L 6 3 L 0 6 z");
		});
	
}
