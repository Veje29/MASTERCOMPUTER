<?php

namespace App\Http\Controllers;

use App\Models\Computer;
use Illuminate\Http\Request;

class ComputerController extends Controller
{
    public function getAll() {
        return response()->jsoN([
            "computers" => Computer::all()
        ]);
    }
}
