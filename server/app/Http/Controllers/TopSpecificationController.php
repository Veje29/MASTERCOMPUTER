<?php

namespace App\Http\Controllers;

use App\Models\TopSpecification;
use Illuminate\Http\Request;

class TopSpecificationController extends Controller
{
    public function getAll() {
        $computers = TopSpecification::all()->pluck("computer");

        return response()->json([
            "computers" => $computers,
            "message" => "Successfully fetched all data",
        ]);
    }
}
