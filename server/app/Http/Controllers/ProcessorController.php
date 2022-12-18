<?php

namespace App\Http\Controllers;

use App\Models\Processor;
use App\Models\ProcessorAmd;
use Illuminate\Http\Request;
use Illuminate\Http\Response;

class ProcessorController extends Controller
{
    public function getAll() {
        return response()->json([
            "processors" => Processor::all()
        ], Response::HTTP_OK);
    }
}
