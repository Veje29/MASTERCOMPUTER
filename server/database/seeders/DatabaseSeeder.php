<?php

namespace Database\Seeders;

// use Illuminate\Database\Console\Seeds\WithoutModelEvents;

use App\Models\Computer;
use App\Models\Motherboard;
use App\Models\PowerSupply;
use App\Models\Processor;
use App\Models\TopSpecification;
use App\Models\User;
use App\Models\Vga;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        // \App\Models\User::factory(10)->create();

        User::create([
            "name" => "Atras Shalhan",
            "email" => "atrasshalhan@gmail.com",
            "password" => bcrypt("testing12345"),
            "phone_number" => "081287318166",
            "photo_profile_path" => '4RReEFCkQc7ZvuM5u86k8vK8VqFeTqBjuLAjZllz.jpg'
        ]);

        User::create([
            "name" => "Bayan",
            "email" => "bayan@gmail.com",
            "password" => bcrypt("testing12345"),
            "phone_number" => "081287318166",
            "photo_profile_path" => "profile_alexis_sanchez.png"
        ]);

        User::create([
            "name" => "Valian Masdani",
            "email" => "valian@gmail.com",
            "password" => bcrypt("testing12345"),
            "phone_number" => "081287318166",
        ]);

        Computer::create([
            "title" => "Gaming E-Sports Blockade",
            "description" => "AMD Ryzen 5 4500 3.6Ghz Up To 4.1Ghz, MSI B450 A Pro MAX,  GALAX Geforce GTX 1660 6GB DDR5 ",
            "price" => "6969800",
            "image_path" => "Gaming_E-Sports_Blockade.png",
            "ranking" => "low"
        ]);

        Computer::create([
            "title" => "Gaming E-Sports Extraordinary",
            "description" => "AMD Ryzen 5 4500 3.6Ghz Up To 4.1Ghz MSI B450 A Pro MAX,XFX Radeon RX 6600 CORE 8GB DDR6, CUBE GAMING WRITE WHITE.",
            "price" => "8999000",
            "image_path" => "Gaming E-Sports Extraordinary.png",
            "ranking" => "low"
        ]);

        Computer::create([
            "title" => "Gaming E-Sports Extraction",
            "description" => "AMD Ryzen 5 3600 3.6Ghz Up To 4.2Ghz Cac, ASRock B550M PG Riptide,KLEVV DDR4 BOLT X Series PC25600 3200MHz,",
            "price" => "9800000",
            "image_path" => "Gaming E-Sports Extraction.png",
            "ranking" => "low"
        ]);

        Computer::create([
            "title" => "Gaming E-Sports Hyssos",
            "description" => "Intel Core i5 10400F 2.9Ghz Up To, ASRock B560M Pro4,  GEIL DDR4 EVO POTENZA 3200MHz Dual Chann, GALAX Geforce GTX 1660 SUPER 6GB DDR6.,",
            "price" => "10899000",
            "image_path" => "Gaming E-Sports Hyssos.png",
            "ranking" => "medium"
        ]);

        Computer::create([
            "title" => "Gaming E-Sports Ezonos",
            "description" => "AMD Ryzen 5 5500 3.6Ghz Up To 4.2Ghz Cac, ASRock B550 Pro4, KLEVV DDR4 BOLT X Series  3600MHz, GALAX GTX 1660 Ti 6GB DDR6",
            "price" => "11399000",
            "image_path" => "image 15.png",
            "ranking" => "medium",
        ]);

        Computer::create([
            "title" => "Gaming E-Sports Edge",
            "description" => "AMD Ryzen 5 5600X 3.7Ghz Up To 4.6Ghz,ASRock B550M Steel Legend,ADATA DDR4 XPG, XFX Radeon RX 6650 DDR5.",
            "price" => "15999000",
            "image_path" => "med_3-removebg-preview 1.png",
            "ranking" => "medium",
        ]);

        Computer::create([
            "title" => "MSI MAG Desktop XII",
            "description" => "MSI GeForce RTX 3080 Ventus 3X 8GB GDDR6, Intel i9-12900F [16C(8P+8E)/20T, MSI MAG B660 Tomahawk WiFi DDR4 [ATX]",
            "price" => "3334100",
            "image_path" => "image-removebg-preview 1.png",
            "ranking" => "high",
        ]);

        Computer::create([
            "title" => "Gaming E-Sports Masterforce",
            "description" => "AMD Ryzen 7 5800X 3.8Ghz Up To 4.7Ghz,Asus TUF Gaming X570 PRO, ADATA DDR4 XPG SPECTRIX D50 WHITE VERSIO, MSI GeForce RTX 3070 Ti 8GB GDDR6X.",
            "price" => "2629900",
            "image_path" => "DDQxLtGUIAEjkFR-removebg-preview 1.png",
            "ranking" => "high",
        ]);

        Computer::create([
            "title" => "Gaming E-Sports Insignia",
            "description" => "Intel Core i7 12700K 3.6GHz Up To, ASRock Z690 Steel Legend,KLEVV DDR4 4000MHz, MSI GeForce RTX 3080 Ti 12GB GDDR6X, PRIME X WHITE PREMIUM GAMING.",
            "price" => "3479900",
            "image_path" => "image 17.png",
            "ranking" => "high",
        ]);

        TopSpecification::create([
            "computer_id" => 1
        ]);
        TopSpecification::create([
            "computer_id" => 2
        ]);

        Processor::create([
            "description" => "Intel Core i9-13900K 3.0GHz Up To 5.8GHz - Cache 36MB [Box] Socket LGA 1700 - Raptor Lake Series",
            "generation" => "i9",
            "type" => "intel",
            "price" => 11000000
        ]);
        Processor::create([
            "description" => "Intel Core i9-13900K 3.0GHz Up To 5.8GHz - Cache 36MB [Box] Socket LGA 1700 - Raptor Lake Series",
            "generation" => "i9",
            "type" => "intel",
            "price" => 10025000
        ]);

        Processor::create([
            "description" => "Intel Core i7-13700K 3.4GHz Up To 5.4GHz - Cache 30MB [Box] Socket LGA 1700 - Raptor Lake Series",
            "generation" => "i7",
            "type" => "intel",
            "price" => 7879000
        ]);
        Processor::create([
            "description" => "Intel Core i7-13700K 3.4GHz Up To 5.4GHz - Cache 30MB [Box] Socket LGA 1700 - Raptor Lake Series",
            "generation" => "i7",
            "type" => "intel",
            "price" => 6820000
        ]);

        Processor::create([
            "description" => "AMD Ryzen 9 5950X 3.4Ghz Up To 4.9Ghz Cache 64MB 105W AM4 [Box] - 16 Core - 100-100000059WOF (Garansi Lokal/AMD",
            "generation" => "amd4",
            "type" => "amd",
            "price" => 9199000
        ]);

        Processor::create([
            "description" => "AMD Ryzen Threadripper PRO 5975WX 3.6Ghz Up To 4.5Ghz Cache 128MB 280W sWRX8 [BOX] - 32 Core - 100-",
            "generation" => "amd4",
            "type" => "amd",
            "price" => 6219000
        ]);

        Processor::create([
            "description" => "AMD Ryzen 9 7950X 4.5Ghz Up To 5.7Ghz Cache 64MB 170W AM5 [Box] - 16 Core - 100-100000514WOF",
            "generation" => "amd5",
            "type" => "amd",
            "price" => 10170000
        ]);

        Processor::create([
            "description" => "AMD Ryzen 9 7900X 4.7Ghz Up To 5.6Ghz Cache 64MB 170W AM5 [Box] - 12 Core - 100-100000589WOF",
            "generation" => "amd5",
            "type" => "amd",
            "price" => 8410000
        ]);

        Vga::create([
            "description" => "GALAX Geforce RTX 3090 Ti 24GB DDR6X HOF VERSION (HALL OF FAME) - ARGB TRIPLE FAN",
            "generation" => "galax",
            "price" => 7500000
        ]);
        Vga::create([
            "description" => "GALAX Geforce RTX 3090 Ti 24GB DDR6X EX Gamer (1-Click OC) - Triple Fan - RGB Effect ",
            "generation" => "galax",
            "price" => 6900000
        ]);
        Vga::create([
            "description" => "XFX Radeon RX 6900 XT RGB EKWB Waterblock 16GB DDR6 SPEEDSTER ZERO - RX-69XTAWBD9",
            "generation" => "xfx",
            "price" => 4200000
        ]);
        Vga::create([
            "description" => "XFX Radeon RX 6900 XT Limited BLACK 16GB DDR6 SPEEDSTER MERC 319 - RX-69XTACSD9",
            "generation" => "xfx",
            "price" => 8900000
        ]);

        Motherboard::create([
            "description" => "Asrock Z790 Steel Legend WIFI (LGA1700, Z790, DDR5, USB3.2 Type-C, SATA3",
            "price" => 1340000
        ]);
        Motherboard::create([
            "description" => "Asrock Z790 LiveMixer WIFI (LGA1700, Z790, DDR5, USB3.2 Type-C, SATA3)",
            "price" => 5300000
        ]);
        Motherboard::create([
            "description" => "Asus ROG MAXIMUS Z790 HERO (LGA1700, Z790, DDR5, USB3.2 Type-C, SATA3",
            "price" => 12090000
        ]);
        Motherboard::create([
            "description" => "Asus ROG Maximus Z690 Formula (LGA1700, Z690, DDR5, USB3.2, SATA3)",
            "price" => 13400000
        ]);

        PowerSupply::create([
            "description" => "PRIME PREMIUM 750W - 80+ Gold Certified Flat Cable Design - Full Modular - Japanese Capacitors - 5 Years Warranty",
            'price' => 1290000
        ]);
        PowerSupply::create([
            "description" => "PRIME PREMIUM 650W - 80+ Gold Certified Flat Cable Design - Full Modular - Japanese Capacitors - 5 Years Warranty",
            'price' => 1190000
        ]);
        PowerSupply::create([
            "description" => "Antec ATOM 550W - V550 (Efficiency 80%) - 2 Years Warranty Replacement",
            'price' => 395000
        ]);
        PowerSupply::create([
            "description" => "Antec ATOM B650 - 650W 80+ Bronze Certified - Flat Cable - 3 Years Warranty Replacement",
            'price' => 4500000
        ]);
    }
}
