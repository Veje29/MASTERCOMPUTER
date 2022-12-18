<?php

namespace App\Http\Controllers;

use App\Models\PowerSupply;
use Illuminate\Http\Request;

class PowerSupplyController extends Controller
{
    public function getAll() {
        return response()->json([
            "power_supplies" => PowerSupply::all()
        ]);
    }
}
