<?php

namespace App\Http\Controllers;

use App\Models\Vga;
use Illuminate\Http\Request;

class VgaController extends Controller
{
    public function getAll() {
        return response()->json([
            "vgas" => Vga::all()
        ], 200);
    }
}
