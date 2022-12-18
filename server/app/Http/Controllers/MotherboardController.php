<?php

namespace App\Http\Controllers;

use App\Models\Motherboard;
use Illuminate\Http\Request;

class MotherboardController extends Controller
{
    public function getAll() {
        return response()->json([
            "motherboards" => Motherboard::all()
        ]);
    }
}
