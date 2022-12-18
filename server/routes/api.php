<?php

use App\Http\Controllers\AuthController;
use App\Http\Controllers\ComputerController;
use App\Http\Controllers\MotherboardController;
use App\Http\Controllers\PowerSupplyController;
use App\Http\Controllers\ProcessorController;
use App\Http\Controllers\TopSpecificationController;
use App\Http\Controllers\UserController;
use App\Http\Controllers\UserSelectController;
use App\Http\Controllers\VgaController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->group(function() {
    Route::post("users/select/processor", [UserSelectController::class, "selectProcessor"]);
    Route::post("users/select/vga", [UserSelectController::class, "selectVga"]);
    Route::post("users/select/motherboard", [UserSelectController::class, "selectMotherboard"]);
    Route::post("users/select/power-supply", [UserSelectController::class, "selectPowerSupply"]);
    Route::get("users/package", [UserSelectController::class, "getPackage"]);
    Route::post("users/profile/photo", [UserSelectController::class, "changeProfilePicture"]);
});

Route::prefix("auth")->group(function() {
    Route::post("login", [AuthController::class, "login"]);
});

Route::post("register/user", [UserController::class, "store"]);

Route::get("computers", [ComputerController::class, "getAll"]);

Route::get("top-specifications", [TopSpecificationController::class, "getAll"]);

Route::get("processors", [ProcessorController::class, "getAll"]);

Route::get("vga", [VgaController::class, "getAll"]);

Route::get("motherboards", [MotherboardController::class, "getAll"]);

Route::get("power-supplies", [PowerSupplyController::class, "getAll"]);
